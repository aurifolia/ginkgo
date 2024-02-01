package org.aurifolia.ginkgo.commons.util;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SpelUtilTest {
    @Test
    void testEntity() {
        HashMap<String, Object> data = new HashMap<>();
        SpelUtil.Entity entity = new SpelUtil.Entity(data);

        assertEquals(entity.add(entity.get("a"), entity.get("b")), "0");
        assertEquals(entity.sub(entity.get("a"), entity.get("b")), "0");
        assertEquals(entity.mul(entity.get("a"), entity.get("b")), "0");
        assertEquals(entity.div(entity.get("a"), entity.get("b")), "0");

        data.put("a", 1);
        assertEquals(entity.add(entity.get("a"), entity.get("b")), "1");
        assertEquals(entity.sub(entity.get("a"), entity.get("b")), "1");
        assertEquals(entity.mul(entity.get("a"), entity.get("b")), "0");
        assertEquals(entity.div(entity.get("a"), entity.get("b")), "0");

        data.put("b", "1.2");
        assertEquals(entity.add(entity.get("a"), entity.get("b")), "2.2");
        assertEquals(entity.sub(entity.get("a"), entity.get("b")), "-0.2");
        assertEquals(entity.mul(entity.get("a"), entity.get("b")), "1.2");
        assertEquals(entity.div(entity.get("a"), entity.get("b")), "0.83333");

        data.put("a", null);
        assertEquals(entity.add(entity.get("a"), entity.get("b")), "1.2");
        assertEquals(entity.sub(entity.get("a"), entity.get("b")), "-1.2");
        assertEquals(entity.mul(entity.get("a"), entity.get("b")), "0");
        assertEquals(entity.div(entity.get("a"), entity.get("b")), "0");

        assertEquals(entity.toPercent("3.14"), "314%");
        assertEquals(entity.toPercent("3.1400"), "314%");
        assertEquals(entity.toPercent("3.14001"), "314%");
        assertEquals(entity.toPercent("3.14004"), "314%");
        assertEquals(entity.toPercent("3.14005"), "314.01%");

        String str = null;
        assertNull(entity.splitNumber(str, 0));
        str = "aa3.14bb-0.12cc10.9%-0+1.-2.%+3.1%0.%-1.+2.1-1.12+2.2";
        assertEquals(entity.splitNumber(str, 0), "3.14");
        assertEquals(entity.splitNumber(str, 1), "-0.12");
        assertEquals(entity.splitNumber(str, 2), "10.9%");
        assertEquals(entity.splitNumber(str, 3), "-0");
        assertEquals(entity.splitNumber(str, 4), "+1.");
        assertEquals(entity.splitNumber(str, 5), "-2.%");
        assertEquals(entity.splitNumber(str, 6), "+3.1%");
        assertEquals(entity.splitNumber(str, 7), "0.%");
        assertEquals(entity.splitNumber(str, 8), "-1.");
        assertEquals(entity.splitNumber(str, 9), "+2.1");
        assertEquals(entity.splitNumber(str, 10), "-1.12");
        assertEquals(entity.splitNumber(str, 11), "+2.2");
    }

}