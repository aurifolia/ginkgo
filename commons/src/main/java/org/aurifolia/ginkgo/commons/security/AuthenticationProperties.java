package org.aurifolia.ginkgo.commons.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * 认证相关的配置
 *
 * @author VNElinpe
 * @since 1.0
 **/
@Data
@ConfigurationProperties("aurifolia.ginkgo.authentication")
public class AuthenticationProperties {
    /**
     * 是否开启认证
     */
    private boolean enable;
    /**
     * 登录url
     */
    private String loginUrl;
    /**
     * 登录信息超时时间
     */
    private Duration loginInfoTtl;
    /**
     * 退出登录url
     */
    private String logoutUrl;
}
