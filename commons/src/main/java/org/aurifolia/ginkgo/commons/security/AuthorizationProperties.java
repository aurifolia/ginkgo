package org.aurifolia.ginkgo.commons.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Set;

/**
 * 权限相关的配置
 *
 * @author VNElinpe
 * @since 1.0
 **/
@Data
@ConfigurationProperties("aurifolia.ginkgo.authorization")
public class AuthorizationProperties {
    /**
     * 对外开放的url列表
     */
    private Set<String> allowedUrlPatterns;
    /**
     * 禁止访问的url列表
     */
    private Set<String> blockedUrlPatterns;
    /**
     * 授权才能访问的url列表
     */
    private List<UrlPerm> urlPerms;

    /**
     * 权限-url模式
     */
    @Data
    public static class UrlPerm {
        private String urlPattern;
        private Set<String> perms;
    }
}
