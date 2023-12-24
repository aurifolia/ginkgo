package org.aurifolia.ginkgo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用户服务
 *
 * @author VNElinpe
 * @since 1.0
 **/
@SpringBootApplication(scanBasePackages = {"org.aurifolia.ginkgo.user", "org.aurifolia.ginkgo.common.core.cache"})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
