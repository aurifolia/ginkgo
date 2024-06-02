package org.aurifolia.ginkgo.common.core.configuration;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;

import static org.springframework.core.io.support.ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX;

/**
 * 向MessageSource里添加用于处理错误码的配置文件
 *
 * @author danpeng
 * @since 1.0
 */
public class ResultMessageApplicationContextInitializer implements PriorityOrdered,
        ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final String BASENAME_KEY = "spring.messages.basename";
    private static final String DEFAULT_BASENAME = "messages";
    private static final String RESULT_MESSAGE_BASENAME = "result-message, validation-message";

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        String basename = environment.getProperty(BASENAME_KEY);
        if (!StringUtils.hasText(basename) && defaultMessageSourceFileExists()) {
            basename = DEFAULT_BASENAME;
        }
        String fullBasename = !StringUtils.hasText(basename) ? RESULT_MESSAGE_BASENAME
                : String.format("%s, %s", basename, RESULT_MESSAGE_BASENAME);
        propertySources.addAfter("configurationProperties",
                new MapPropertySource("messageSourceConfig", Map.of(BASENAME_KEY, fullBasename)));
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    /**
     * 判断默认国际化配置文件是否存在
     *
     * @return true if exists, false if not
     */
    private boolean defaultMessageSourceFileExists() {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            return resolver.getResources(String.format("%sMETA-INF/%s*",
                    CLASSPATH_ALL_URL_PREFIX, RESULT_MESSAGE_BASENAME)).length > 0;
        } catch (IOException e) {
            // ignore
        }
        return false;
    }
}
