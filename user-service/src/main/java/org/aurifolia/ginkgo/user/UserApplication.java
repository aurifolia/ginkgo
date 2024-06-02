package org.aurifolia.ginkgo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * 用户服务
 *
 * @author danpeng
 * @since 1.0
 */
@SpringBootApplication
public class UserApplication {
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
