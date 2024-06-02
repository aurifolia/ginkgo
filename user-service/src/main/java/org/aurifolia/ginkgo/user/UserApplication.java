package org.aurifolia.ginkgo.user;

import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;

/**
 * 用户服务
 *
 * @author danpeng
 * @since 1.0
 */
@SpringBootApplication
public class UserApplication implements ApplicationRunner, ApplicationContextAware {
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        MessageSource messageSource = applicationContext.getBean(MessageSource.class);
        String t001 = messageSource.getMessage("t001", null, null);
        System.out.println();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
