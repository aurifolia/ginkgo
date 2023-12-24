package org.aurifolia.ginkgo.gateway.outer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 对外的网关服务
 *
 * @author VNElinpe
 * @since 1.0
 **/
@SpringBootApplication(scanBasePackages = {"org.aurifolia.ginkgo.gateway.outer",
        "org.aurifolia.ginkgo.common.core.cache"})
public class OuterGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(OuterGatewayApplication.class, args);
    }
}
