package org.aurifolia.ginkgo.common.core.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动扫描
 *
 * @author danpeng
 * @since 1.0
 */
@Configuration
@ComponentScan(basePackages = "org.aurifolia.ginkgo.common.core")
public class CommonCoreAutoConfiguration {

}
