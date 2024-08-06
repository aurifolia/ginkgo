package org.aurifolia.ginkgo.user.api.impl;

import jakarta.validation.ConstraintViolationException;
import org.aurifolia.common.api.dto.Response;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 通用异常处理
 *
 * @author danpeng
 * @since 1.0
 */
@RestControllerAdvice
@ConditionalOnClass(RestControllerAdvice.class)
public class GlobalExceptionHandler {
    /**
     * 处理参数校验错误
     *
     * @param e 参数校验错误信息
     * @return 返回信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handle(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        return null;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handle(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().findFirst().get().getMessage();

        return null;
    }
}
