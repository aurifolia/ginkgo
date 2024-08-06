package org.aurifolia.common.api.configuration;

import java.util.List;

/**
 * 注册通用响应码的国际化文件
 *
 * @author danpeng
 * @since 1.0
 */
public class CommonResultMessageRegistry implements ResultMessageRegistry {
    @Override
    public List<String> getResultMessageNames() {
        return List.of("common-result-message");
    }
}
