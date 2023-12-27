package org.aurifolia.ginkgo.commons.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.aurifolia.ginkgo.commons.cache.RedisTemplate4CacheConfiguration;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.util.ClassUtils;

import java.util.List;

/**
 * 获取ObjectMapper单例
 *
 * @author VNElinpe
 * @since 1.0
 **/
public class ObjectMapperHolder {
    private static final boolean securityJackson2ModulesPresent = ClassUtils.isPresent("org.springframework.security" +
            ".jackson2.SecurityJackson2Modules", ObjectMapperHolder.class.getClassLoader());
    private static final boolean oauth2Jackson2ModulePresent = ClassUtils.isPresent("org.springframework.security" +
            ".oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module",
            ObjectMapperHolder.class.getClassLoader());

    @Getter
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        configure(objectMapper);
    }

    /**
     * 反序列化json
     *
     * @param json json
     * @param typeReference TypeReference
     * @return T
     * @param <T> <T>
     */
    public static <T> T readValue(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json序列化
     * @param object object
     * @return json
     */
    public static String writeValue(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 配置ObjectMapper
     *
     * @param objectMapper objectMapper
     */
    public static void configure(ObjectMapper objectMapper) {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        if (securityJackson2ModulesPresent) {
            List<Module> securityModules = SecurityJackson2Modules
                    .getModules(RedisTemplate4CacheConfiguration.class.getClassLoader());
            objectMapper.registerModules(securityModules);
        }
        if (oauth2Jackson2ModulePresent) {
            objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());
        }
    }
}
