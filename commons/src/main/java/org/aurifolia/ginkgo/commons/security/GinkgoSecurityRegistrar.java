package org.aurifolia.ginkgo.commons.security;

import org.aurifolia.ginkgo.commons.cache.RedisTemplate4CacheConfiguration;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 注册一些必要Bean
 *
 * @author VNElinpe
 * @since 1.0
 **/
public class GinkgoSecurityRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry,
                                        BeanNameGenerator importBeanNameGenerator) {
        BeanDefinition authenticationBeanDefinition = new AnnotatedGenericBeanDefinition(AuthenticationProperties.class);
        String authenticationBeanName = importBeanNameGenerator.generateBeanName(authenticationBeanDefinition, registry);
        if (!registry.containsBeanDefinition(authenticationBeanName)) {
            registry.registerBeanDefinition(authenticationBeanName, authenticationBeanDefinition);
        }
        BeanDefinition authorizationBeanDefinition = new AnnotatedGenericBeanDefinition(AuthorizationProperties.class);
        String authorizationBeanName = importBeanNameGenerator.generateBeanName(authorizationBeanDefinition, registry);
        if (!registry.containsBeanDefinition(authorizationBeanName)) {
            registry.registerBeanDefinition(authorizationBeanName, authorizationBeanDefinition);
        }
        BeanDefinition webSecurityBeanDefinition = new AnnotatedGenericBeanDefinition(GinkgoWebSecurityConfiguration.class);
        String webSecurityBeanName = importBeanNameGenerator.generateBeanName(webSecurityBeanDefinition, registry);
        if (!registry.containsBeanDefinition(webSecurityBeanName)) {
            registry.registerBeanDefinition(webSecurityBeanName, webSecurityBeanDefinition);
        }
        BeanDefinition redisTemplateBeanDefinition = new AnnotatedGenericBeanDefinition(RedisTemplate4CacheConfiguration.class);
        String redisTemplateBeanName = importBeanNameGenerator.generateBeanName(redisTemplateBeanDefinition, registry);
        if (!registry.containsBeanDefinition(redisTemplateBeanName)) {
            registry.registerBeanDefinition(redisTemplateBeanName, redisTemplateBeanDefinition);
        }
    }
}
