package org.aurifolia.ginkgo.commons.util;

import java.util.UUID;

/**
 * 随机数相关的工具类
 *
 * @author VNElinpe
 * @since 1.0
 **/
public class RandomUtil {
    /**
     * 压缩后的uuid长度
     */
    public static final int COMPACTED_UUID_LENGTH = 32;

    /**
     * 获取长度为32的uuid字符串
     *
     * @return uuid字符串
     */
    public static  String getUUID() {
        return getUUID(true);
    }

    /**
     * 获取uuid字符串
     *
     * @param compact 返回结果是否去除"-"
     * @return uuid字符串
     */
    public static String getUUID(boolean compact) {
        String uuid = UUID.randomUUID().toString();
        return compact ? uuid.replace("-", "") : uuid;
    }
}
