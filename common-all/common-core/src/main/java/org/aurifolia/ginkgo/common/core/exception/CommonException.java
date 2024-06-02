package org.aurifolia.ginkgo.common.core.exception;

import lombok.Getter;

import java.io.Serial;

/**
 * 通用异常
 *
 * @author danpeng
 * @since 1.0
 */
@Getter
public class CommonException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 3405663502358137481L;

    /**
     * 错误码
     */
    private final String code;
    /**
     * 错误信息
     */
    private String message;

    public CommonException(String code) {
        this.code = code;
    }

    public CommonException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
