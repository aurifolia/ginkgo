package org.aurifolia.common.api.util;

import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 与校验相关的工具类
 *
 * @author danpeng
 * @since 1.0
 */
public class ValidationUtil {
    /**
     * 校验对象是否为空
     *
     * @param obj 对象
     * @return 是否为空
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String string) {
            return !StringUtils.hasText(string);
        }
        if (obj.getClass().isArray()) {
            return ((Object[]) obj).length == 0;
        }
        if (obj instanceof Collection<?> collection) {
            return collection.isEmpty();
        }
        if (obj instanceof Map<?,?> map) {
            return map.isEmpty();
        }
        return false;
    }
}
