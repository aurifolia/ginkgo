package org.aurifolia.common.api.configuration;

import java.util.List;

/**
 * @author danpeng
 * @since 1.0
 */
public interface ResultMessageRegistry {
    /**
     * 获取返回信息国际化文件文件名
     *
     * @return 文件名列表
     */
    List<String> getResultMessageNames();
}
