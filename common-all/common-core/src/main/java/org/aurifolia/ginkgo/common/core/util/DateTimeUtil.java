package org.aurifolia.ginkgo.common.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.time.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 时间相关工具类
 *
 * @author danpeng
 * @since 1.0
 */
public class DateTimeUtil {
    private static final Map<String, ZoneId> ZONE_ID_CACHE = new ConcurrentHashMap<>(4);
    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();
    private static final Logger log = LoggerFactory.getLogger(DateTimeUtil.class);

    /**
     * 将ZonedDateTime转换到指定时区, 会改变时间
     *
     * @param zonedDateTime ZonedDateTime
     * @param zoneId        ZoneId
     * @return ZonedDateTime
     */
    public static ZonedDateTime withZoneSameInstant(ZonedDateTime zonedDateTime, ZoneId zoneId) {
        return zonedDateTime.withZoneSameInstant(zoneId);
    }

    /**
     * 将ZonedDateTime转换到指定时区, 不改变时间
     *
     * @param zonedDateTime ZonedDateTime
     * @param zoneId        ZoneId
     * @return ZonedDateTime
     */
    public static ZonedDateTime withZoneSameLocal(ZonedDateTime zonedDateTime, ZoneId zoneId) {
        return zonedDateTime.withZoneSameLocal(zoneId);
    }

    /**
     * 将LocalDateTime转换到指定时区, 会改变时间
     *
     * @param localDateTime LocalDateTime
     * @param zoneId        ZoneId
     * @return LocalDateTime
     */
    public static LocalDateTime withZoneSameInstant(LocalDateTime localDateTime, ZoneId zoneId) {
        return withZoneSameInstant(localDateTime, DEFAULT_ZONE_ID, zoneId);
    }

    /**
     * 将LocalDateTime转换到指定时区, 会改变时间
     *
     * @param localDateTime LocalDateTime
     * @param from          源时区
     * @param to            目标时区
     * @return LocalDateTime
     */
    public static LocalDateTime withZoneSameInstant(LocalDateTime localDateTime, ZoneId from, ZoneId to) {
        return from.equals(to) ? localDateTime
                : localDateTime.atZone(from).withZoneSameInstant(to).toLocalDateTime();
    }

    /**
     * 将OffsetDateTime转换到指定时区, 会改变时间
     *
     * @param offsetDateTime OffsetDateTime
     * @param zoneId         ZoneId
     * @return OffsetDateTime
     */
    public static OffsetDateTime withZoneSameInstant(OffsetDateTime offsetDateTime, ZoneId zoneId) {
        return offsetDateTime.withOffsetSameInstant(zoneId.getRules().getOffset(Instant.now()));
    }

    /**
     * 将OffsetDateTime转换到指定时区, 不改变时间
     *
     * @param offsetDateTime OffsetDateTime
     * @param zoneId         ZoneId
     * @return OffsetDateTime
     */
    public static OffsetDateTime withZoneSameLocal(OffsetDateTime offsetDateTime, ZoneId zoneId) {
        return offsetDateTime.withOffsetSameLocal(zoneId.getRules().getOffset(Instant.now()));
    }

    /**
     * 获取ZoneId
     *
     * @param zoneId ZoneIdStr
     * @return ZoneId
     */
    public static ZoneId getZoneIdWithCache(String zoneId) {
        if (!StringUtils.hasText(zoneId)) {
            return null;
        }
        if (!ZONE_ID_CACHE.containsKey(zoneId)) {
            synchronized (DateTimeUtil.class) {
                if (!ZONE_ID_CACHE.containsKey(zoneId)) {
                    ZoneId target = null;
                    try {
                        target = ZoneId.of(zoneId);
                    } catch (Exception e) {
                        // ignore
                        log.error("parse zoneId for {} failed.", zoneId);
                    }
                    ZONE_ID_CACHE.put(zoneId, target);
                }
            }
        }
        return ZONE_ID_CACHE.get(zoneId);
    }

    /**
     * 获取默认ZoneId
     *
     * @return ZoneId
     */
    public static ZoneId getDefaultZoneId() {
        return DEFAULT_ZONE_ID;
    }
}
