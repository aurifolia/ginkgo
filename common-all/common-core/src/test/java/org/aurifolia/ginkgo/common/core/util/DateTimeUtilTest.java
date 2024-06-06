package org.aurifolia.ginkgo.common.core.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.aurifolia.ginkgo.common.core.constant.CommonConstant.DEFAULT_DATE_TIME_FORMATTER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DateTimeUtilTest {

    @Test
    void withZoneSameInstant() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime zonedDateTime = DateTimeUtil.withZoneSameInstant(now, ZoneId.of("Asia/Shanghai"));
        assertNotEquals(now.format(DEFAULT_DATE_TIME_FORMATTER), zonedDateTime.format(DEFAULT_DATE_TIME_FORMATTER));
        assertNotEquals(now.getZone(), zonedDateTime.getZone());

        zonedDateTime = DateTimeUtil.withZoneSameInstant(zonedDateTime, ZoneId.of("UTC"));
        assertEquals(now.format(DEFAULT_DATE_TIME_FORMATTER), zonedDateTime.format(DEFAULT_DATE_TIME_FORMATTER));
        assertEquals(now.getZone(), zonedDateTime.getZone());
    }

    @Test
    void withZoneSameLocal() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime zonedDateTime = DateTimeUtil.withZoneSameLocal(now, ZoneId.of("Asia/Shanghai"));
        assertEquals(now.format(DEFAULT_DATE_TIME_FORMATTER), zonedDateTime.format(DEFAULT_DATE_TIME_FORMATTER));
        assertNotEquals(now.getZone(), zonedDateTime.getZone());

        zonedDateTime = DateTimeUtil.withZoneSameLocal(zonedDateTime, ZoneId.of("UTC"));
        assertEquals(now.format(DEFAULT_DATE_TIME_FORMATTER), zonedDateTime.format(DEFAULT_DATE_TIME_FORMATTER));
        assertEquals(now.getZone(), zonedDateTime.getZone());
    }

    @Test
    void testWithZoneSameInstant() {
        LocalDateTime utc = LocalDateTime.now(ZoneId.of("UTC"));
        LocalDateTime utc1 = DateTimeUtil.withZoneSameInstant(utc, ZoneId.of("UTC"), ZoneId.of("Asia/Shanghai"));
        assertNotEquals(utc.format(DEFAULT_DATE_TIME_FORMATTER), utc1.format(DEFAULT_DATE_TIME_FORMATTER));

        utc1 = DateTimeUtil.withZoneSameInstant(utc1, ZoneId.of("Asia/Shanghai"), ZoneId.of("UTC"));
        assertEquals(utc.format(DEFAULT_DATE_TIME_FORMATTER), utc1.format(DEFAULT_DATE_TIME_FORMATTER));
    }

    @Test
    void testWithZoneSameInstant1() {
        OffsetDateTime utc = OffsetDateTime.now(ZoneId.of("UTC"));
        OffsetDateTime offsetDateTime = DateTimeUtil.withZoneSameInstant(utc, ZoneId.of("Asia/Shanghai"));
        assertNotEquals(utc.format(DEFAULT_DATE_TIME_FORMATTER), offsetDateTime.format(DEFAULT_DATE_TIME_FORMATTER));
        assertNotEquals(utc.getOffset(), offsetDateTime.getOffset());

        offsetDateTime = DateTimeUtil.withZoneSameInstant(utc, ZoneId.of("UTC"));
        assertEquals(utc.format(DEFAULT_DATE_TIME_FORMATTER), offsetDateTime.format(DEFAULT_DATE_TIME_FORMATTER));
        assertEquals(utc.getOffset(), offsetDateTime.getOffset());
    }

    @Test
    void testWithZoneSameLocal() {
        OffsetDateTime utc = OffsetDateTime.now(ZoneId.of("UTC"));
        OffsetDateTime offsetDateTime = DateTimeUtil.withZoneSameLocal(utc, ZoneId.of("Asia/Shanghai"));
        assertEquals(utc.format(DEFAULT_DATE_TIME_FORMATTER), offsetDateTime.format(DEFAULT_DATE_TIME_FORMATTER));
        assertNotEquals(utc.getOffset(), offsetDateTime.getOffset());

        offsetDateTime = DateTimeUtil.withZoneSameLocal(utc, ZoneId.of("UTC"));
        assertEquals(utc.format(DEFAULT_DATE_TIME_FORMATTER), offsetDateTime.format(DEFAULT_DATE_TIME_FORMATTER));
        assertEquals(utc.getOffset(), offsetDateTime.getOffset());
    }
}