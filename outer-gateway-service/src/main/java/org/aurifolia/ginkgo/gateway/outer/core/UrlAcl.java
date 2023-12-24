package org.aurifolia.ginkgo.gateway.outer.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * TODO
 *
 * @author VNElinpe
 * @since 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "url-acl")
public class UrlAcl {
    /**
     * 无论是否登录, 都允许访问的url集合
     */
    private Set<String> allowSet;
    /**
     * 无论是否登录, 都不允许访问的url集合
     */
    private Set<String> blockSet;
}
