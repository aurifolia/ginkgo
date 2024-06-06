package org.aurifolia.ginkgo.common.core.aop;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.aurifolia.ginkgo.common.core.annotation.ParameterExtends;
import org.aurifolia.ginkgo.common.core.dto.ResultDTO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.beans.Introspector;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.aurifolia.ginkgo.common.core.constant.CommonConstant.HEADER_ZONE_ID;
import static org.aurifolia.ginkgo.common.core.util.DateTimeUtil.*;

/**
 * @author danpeng
 * @since 1.0
 */
@Slf4j
@Aspect
@Component
@ConditionalOnClass(Aspect.class)
public class ParameterExtendsAOP {
    private final MessageSource messageSource;
    private final Map<Class<?>, List<MethodHandleBundle>> classCandidateProperties = new ConcurrentHashMap<>(128);

    public ParameterExtendsAOP(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * pointcut
     */
    @Pointcut("@annotation(org.aurifolia.ginkgo.common.core.annotation.ParameterExtends)")
    private void pointcut() {

    }

    /**
     * 前置处理
     *
     * @param joinPoint JoinPoint
     */
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        ParameterExtends parameterExtends = getParameterExtends(joinPoint);
        ZoneId zoneId;
        if (!parameterExtends.transformTimeZone() || (zoneId = getTargetZoneId()) == null) {
            return;
        }
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }
        handle(args, zoneId, true);
    }

    /**
     * 后置处理
     *
     * @param result 返参
     */
    @AfterReturning(pointcut = "pointcut()", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        ParameterExtends parameterExtends = getParameterExtends(joinPoint);
        if (!parameterExtends.fillResultMessage()) {
            return;
        }
        if (result instanceof ResultDTO<?> resultDTO
                && StringUtils.hasText(resultDTO.getCode())
                && !StringUtils.hasText(resultDTO.getMessage())) {
            try {
                resultDTO.setMessage(messageSource.getMessage(resultDTO.getCode(),
                        null, LocaleContextHolder.getLocale()));
            } catch (Exception e) {
                // ignore
                log.error("cannot fill result message for code {} with message source", resultDTO.getCode());
            }
        }
        // 时区转换
        ZoneId zoneId;
        if (!parameterExtends.transformTimeZone() || (zoneId = getTargetZoneId()) == null) {
            return;
        }
        handle(result, zoneId, false);
    }

    /**
     * 获取客户端ZoneId, 只有当前环境为http, 同时请求头里包含zoneId, 此外, 客户端的时区与服务端的时区不一致时，才返回有效ZoneId
     *
     * @return ZoneId
     */
    private ZoneId getTargetZoneId() {
        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ra == null) {
            log.error("enabled transforming timezone, but current context is not http");
            return null;
        }
        ZoneId zoneId = getZoneIdWithCache(ra.getRequest().getHeader(HEADER_ZONE_ID));
        if (zoneId == null) {
            log.error("http header is not contained zoneId");
            return null;
        }
        return zoneId.equals(getDefaultZoneId()) ? null : zoneId;
    }

    /**
     * 获取方法上的ParameterExtends
     *
     * @param joinPoint JoinPoint
     * @return ParameterExtends
     */
    private ParameterExtends getParameterExtends(JoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(ParameterExtends.class);
    }

    /**
     * 解析class里的时间侯选属性，放入缓存
     *
     * @param obj        Object
     * @param fromClient 是否是从客户端到服务端
     */
    public Object handle(Object obj, ZoneId zoneId, boolean fromClient) {
        if (obj == null) {
            return null;
        }
        Class<?> aClass = obj.getClass();
        // 基本类型和String直接返回
        if (ClassUtils.isPrimitiveOrWrapper(aClass) || aClass == String.class) {
            return null;
        }

        // 受支持的类型
        if (aClass == ZonedDateTime.class) {
            ZonedDateTime handled = fromClient ? withZoneSameLocal((ZonedDateTime) obj, zoneId)
                    : withZoneSameInstant((ZonedDateTime) obj, zoneId);
            return handled == obj ? null : handled;
        } else if (aClass == LocalDateTime.class) {
            LocalDateTime handled = fromClient ? withZoneSameInstant((LocalDateTime) obj, zoneId, getDefaultZoneId())
                    : withZoneSameInstant((LocalDateTime) obj, zoneId);
            return handled == obj ? null : handled;
        } else if (aClass == OffsetDateTime.class) {
            OffsetDateTime handled = fromClient ? withZoneSameLocal((OffsetDateTime) obj, zoneId)
                    : withZoneSameInstant((OffsetDateTime) obj, zoneId);
            return handled == obj ? null : handled;
        }
        // 复合类型
        if (obj instanceof List) {
            handleList((List<Object>) obj, zoneId, fromClient);
        } else if (obj instanceof Map) {
            handleMap((Map<Object, Object>) obj, zoneId, fromClient);
        } else if (aClass.isArray()) {
            handleArray((Object[]) obj, zoneId, fromClient);
        } else if (obj instanceof Set) {
            handleSet((Set<Object>) obj, zoneId, fromClient);
        } else {
            handleBean(obj, zoneId, fromClient, aClass);
        }
        return null;
    }

    private void handleArray(Object[] obj, ZoneId zoneId, boolean fromClient) {
        for (int i = 0; i < obj.length; i++) {
            Object handled = handle(obj[i], zoneId, fromClient);
            if (handled != null) {
                obj[i] = handled;
            }
        }
    }

    private void handleMap(Map<Object, Object> obj, ZoneId zoneId, boolean fromClient) {
        obj.forEach((k, v) -> {
            Object handled = handle(v, zoneId, fromClient);
            if (handled != null) {
                obj.put(k, v);
            }
        });
    }

    private void handleList(List<Object> obj, ZoneId zoneId, boolean fromClient) {
        int loc = 0;
        int size = obj.size();
        for (int i = 0; i < size; i++) {
            Object handled = handle(obj.get(i), zoneId, fromClient);
            if (handled != null) {
                obj.set(loc, handled);
            }
            loc++;
        }
    }

    private void handleSet(Set<Object> obj, ZoneId zoneId, boolean fromClient) {
        List<Object> removed = new ArrayList<>();
        List<Object> added = new ArrayList<>();
        for (Object o : obj) {
            Object handled = handle(o, zoneId, fromClient);
            if (handled != null) {
                removed.add(o);
                added.add(handled);
            }
        }
        if (!removed.isEmpty()) {
            removed.forEach(obj::remove);
            obj.addAll(added);
        }
    }

    private void handleBean(Object obj, ZoneId zoneId, boolean fromClient, Class<?> aClass) {
        List<MethodHandleBundle> methodHandleBundles = classCandidateProperties.get(aClass);
        if (methodHandleBundles == null) {
            makeCache(aClass);
            methodHandleBundles = classCandidateProperties.get(aClass);
        }
        if (methodHandleBundles.isEmpty()) {
            return;
        }
        for (MethodHandleBundle methodHandleBundle : methodHandleBundles) {
            try {
                Object handled = handle(methodHandleBundle.getReadMethodHandle().invoke(obj), zoneId, fromClient);
                if (handled != null) {
                    methodHandleBundle.getWriteMethodHandle().invoke(obj, handled);
                }
            } catch (Throwable e) {
                // ignore
                log.error("transform timezone failed.", e);
            }
        }
    }

    /**
     * 解析Class的所有带Getter, Setter的字段
     *
     * @param aClass Class
     */
    private void makeCache(Class<?> aClass) {
        synchronized (aClass) {
            if (classCandidateProperties.containsKey(aClass)) {
                return;
            }
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            List<MethodHandleBundle> list = Collections.emptyList();
            try {
                list = Arrays.stream(Introspector.getBeanInfo(aClass).getPropertyDescriptors())
                        .map(item -> {
                            Method readMethod = item.getReadMethod();
                            Method writeMethod = item.getWriteMethod();
                            if (readMethod == null || writeMethod == null) {
                                return null;
                            }
                            MethodHandleBundle methodHandleBundle = new MethodHandleBundle();
                            try {
                                methodHandleBundle.setReadMethodHandle(lookup.unreflect(readMethod));
                                methodHandleBundle.setWriteMethodHandle(lookup.unreflect(writeMethod));
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                            return methodHandleBundle;
                        }).filter(Objects::nonNull).collect(Collectors.toList());
            } catch (Exception e) {
                // ignore
                log.error("parse class for transforming timezone failed.", e);
            }
            classCandidateProperties.put(aClass, list);
        }
    }

    @Data
    private static class MethodHandleBundle {
        private MethodHandle readMethodHandle;
        private MethodHandle writeMethodHandle;
    }
}
