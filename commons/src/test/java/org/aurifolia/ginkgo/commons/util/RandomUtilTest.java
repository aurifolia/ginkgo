package org.aurifolia.ginkgo.commons.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomUtilTest {

    @Test
    void getUUID() {
        String uuid1 = RandomUtil.getUUID();
        String uuid2 = RandomUtil.getUUID();
        assertEquals(uuid1.length(), 32);
        assertEquals(uuid2.length(), 32);
        assertNotEquals(uuid1, uuid2);
    }

    @Test
    void testGetUUID() {
        String uuid1 = RandomUtil.getUUID(true);
        String uuid2 = RandomUtil.getUUID(true);
        String uuid3 = RandomUtil.getUUID(false);
        String uuid4 = RandomUtil.getUUID(false);
        assertEquals(uuid1.length(), 32);
        assertEquals(uuid2.length(), 32);
        assertNotEquals(uuid1, uuid2);
        assertEquals(uuid3.length(), 36);
        assertEquals(uuid4.length(), 36);
        assertNotEquals(uuid3, uuid4);
    }
}