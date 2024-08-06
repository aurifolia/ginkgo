package org.aurifolia.common.api.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 字段联合校验
 *
 * @author danpeng
 * @since 1.0
 */
public abstract class FieldsUnionValidator<A extends Annotation, T> implements ConstraintValidator<A, T> {
    private volatile List<MethodHandle> methodHandles;

    @Override
    public boolean isValid(T value, ConstraintValidatorContext context) {
        if (methodHandles == null) {
            synchronized (this) {
                if (methodHandles == null) {
                    Class<?> aClass = value.getClass();
                    Set<String> fieldNames = getFieldNames(aClass);
                    List<MethodHandle> handles = new ArrayList<>(fieldNames.size());
                    MethodHandles.Lookup lookup;
                    try {
                        lookup = MethodHandles.privateLookupIn(aClass, MethodHandles.lookup());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    ReflectionUtils.doWithFields(aClass, field -> {
                        if (fieldNames.contains(field.getName())) {
                            try {
                                handles.add(lookup.findGetter(aClass, field.getName(), field.getType()));
                            } catch (NoSuchFieldException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                    methodHandles = Collections.unmodifiableList(handles);
                }
            }
        }
        return isValid(value, methodHandles);
    }

    /**
     * 获取字段名集合
     *
     * @param aClass 注解所在类
     * @return 字段名集合
     */
    public abstract Set<String> getFieldNames(Class<?> aClass);

    /**
     * 检测是否有效
     *
     * @param value 对象
     * @param methodHandles 字段列表
     * @return 是否有效
     */
    public abstract boolean isValid(T value, List<MethodHandle> methodHandles);
}
