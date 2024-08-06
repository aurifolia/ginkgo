package org.aurifolia.common.api.configuration;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.core.io.support.ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX;

/**
 * 向MessageSource里添加用于处理响应码的国际化配置文件
 *
 * @author danpeng
 * @since 1.0
 */
public class ResultMessageApplicationContextInitializer implements PriorityOrdered,
        ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final String BASENAME_KEY = "spring.messages.basename";
    private static final String DEFAULT_BASENAME = "messages";

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        String basename = environment.getProperty(BASENAME_KEY);
        if (!StringUtils.hasText(basename) && defaultMessageSourceFileExists()) {
            basename = DEFAULT_BASENAME;
        }
        Set<String> resultMessages = SpringFactoriesLoader.forDefaultResourceLocation(this.getClass().getClassLoader())
                .load(ResultMessageRegistry.class).stream()
                .flatMap(item -> item.getResultMessageNames().stream())
                .collect(Collectors.toSet());
        // spring.messages.basename已经包含了所有注册器返回的国际化文件名, 直接返回
        if (StringUtils.commaDelimitedListToSet(basename).containsAll(resultMessages)) {
            return;
        }
        String resultMessageStr = StringUtils.collectionToCommaDelimitedString(resultMessages);
        String fullBasename = !StringUtils.hasText(basename) ? resultMessageStr
                : String.format("%s,%s", basename, resultMessageStr);
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
            return resolver.getResources(String.format("%s%s*", CLASSPATH_ALL_URL_PREFIX, DEFAULT_BASENAME)).length > 0;
        } catch (IOException e) {
            // ignore
        }
        return false;
    }
}
