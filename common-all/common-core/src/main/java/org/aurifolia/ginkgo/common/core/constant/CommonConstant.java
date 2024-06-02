package org.aurifolia.ginkgo.common.core.constant;

import java.time.format.DateTimeFormatter;

/**
 * 通用常量
 *
 * @author danpeng
 * @since 1.0
 */
public class CommonConstant {
    /**
     * 默认日期时间格式
     */
    public static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认日期时间格式
     */
    public static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTERN);
    /**
     * 默认日期格式
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 默认日期格式
     */
    public static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN);
    /**
     * 默认时间格式
     */
    public static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";
    /**
     * 默认时间格式
     */
    public static final DateTimeFormatter DEFAULT_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_TIME_PATTERN);
}
