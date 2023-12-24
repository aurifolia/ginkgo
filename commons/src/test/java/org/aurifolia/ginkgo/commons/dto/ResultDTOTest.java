package org.aurifolia.ginkgo.commons.dto;

import org.aurifolia.ginkgo.commons.constant.ResultCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultDTOTest {

    @Test
    void success() {
        ResultDTO<Object> success = ResultDTO.success();
        assertEquals(success.getCode(), ResultCode.SUCCESS);
        assertNull(success.getMessage());
        assertNull(success.getData());
    }

    @Test
    void testSuccess() {
        Object data = new Object();
        ResultDTO<Object> success = ResultDTO.success(data);
        assertEquals(success.getCode(), ResultCode.SUCCESS);
        assertNull(success.getMessage());
        assertEquals(success.getData(), data);
    }

    @Test
    void fail() {
        ResultDTO<Void> fail = ResultDTO.fail();
        assertEquals(fail.getCode(), ResultCode.SERVICE_ERROR);
        assertNull(fail.getMessage());
        assertNull(fail.getData());
    }

    @Test
    void from() {
        ResultDTO<String> from = ResultDTO.from("code", "data");
        assertEquals(from.getCode(), "code");
        assertNull(from.getMessage());
        assertEquals(from.getData(), "data");
    }

    @Test
    void testFrom() {
        ResultDTO<String> from = ResultDTO.from("code", "message", "data");
        assertEquals(from.getCode(), "code");
        assertEquals(from.getMessage(), "message");
        assertEquals(from.getData(), "data");
    }
}