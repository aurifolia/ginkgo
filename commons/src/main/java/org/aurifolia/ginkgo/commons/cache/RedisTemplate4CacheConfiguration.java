package org.aurifolia.ginkgo.commons.cache;

import com.fasterxml.jackson.databind.Module;
import org.aurifolia.ginkgo.commons.core.ObjectMapperHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.util.ClassUtils;

import java.util.List;

/**
 * 适用于缓存的RedisTemplate配置
 *
 * @author VNElinpe
 * @since 2023/10/5
 **/
@Configuration
public class RedisTemplate4CacheConfiguration {
    private boolean securityJackson2ModulesPresent = ClassUtils.isPresent("org.springframework.security" +
            ".jackson2.SecurityJackson2Modules", this.getClass().getClassLoader());
    private boolean oauth2Jackson2ModulePresent = ClassUtils.isPresent("org.springframework.security.oauth2.server" +
            ".authorization.jackson2.OAuth2AuthorizationServerJackson2Module", this.getClass().getClassLoader());

    /**
     * key用String序列化, value用json序列化
     *
     * @param connectionFactory redis连接工厂
     * @return RedisTemplate
     */
    @Bean("redisTemplate4Cache")
    @ConditionalOnClass(RedisTemplate.class)
    @ConditionalOnMissingBean(name = "redisTemplate4Cache")
    public <V> RedisTemplate<String, V> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, V> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        RedisSerializer<String> stringSerializer = RedisSerializer.string();
        GenericJackson2JsonRedisSerializer jsonSerializer = getJsonSerializer();
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setValueSerializer(jsonSerializer);
        template.setHashValueSerializer(jsonSerializer);
        return template;
    }

    /**
     * key用String序列化, value用json序列化
     *
     * @param connectionFactory redis连接工厂
     * @return ReactiveRedisTemplate
     */
    @Bean("reactiveRedisTemplate4Cache")
    @ConditionalOnClass(ReactiveRedisTemplate.class)
    @ConditionalOnMissingBean(name = "reactiveRedisTemplate4Cache")
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        GenericJackson2JsonRedisSerializer jsonSerializer = getJsonSerializer();
        RedisSerializer<String> stringSerializer = RedisSerializer.string();
        RedisSerializationContext<String, Object> serializationContext =
                RedisSerializationContext.<String, Object>newSerializationContext()
                .key(stringSerializer)
                .hashKey(stringSerializer)
                .value(jsonSerializer)
                .hashValue(jsonSerializer)
                .build();
        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }

    private GenericJackson2JsonRedisSerializer getJsonSerializer() {
        GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();
        jsonSerializer.configure(objectMapper -> {
            ObjectMapperHolder.configure(objectMapper);
            if (securityJackson2ModulesPresent) {
                List<Module> securityModules = SecurityJackson2Modules
                        .getModules(RedisTemplate4CacheConfiguration.class.getClassLoader());
                objectMapper.registerModules(securityModules);
            }
            if (oauth2Jackson2ModulePresent) {
                objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());
            }
        });
        return jsonSerializer;
    }
}
