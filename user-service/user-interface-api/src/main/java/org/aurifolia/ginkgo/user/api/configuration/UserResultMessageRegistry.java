package org.aurifolia.ginkgo.user.api.configuration;

import org.aurifolia.common.api.configuration.ResultMessageRegistry;

import java.util.List;

/**
 * 注册用户响应码的国际化文件
 *
 * @author danpeng
 * @since 1.0
 */
public class UserResultMessageRegistry implements ResultMessageRegistry {
    @Override
    public List<String> getResultMessageNames() {
        return List.of("user-result-message");
    }
}
