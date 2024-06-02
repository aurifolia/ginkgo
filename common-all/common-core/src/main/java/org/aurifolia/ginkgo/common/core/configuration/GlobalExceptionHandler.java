package org.aurifolia.ginkgo.common.core.configuration;

import org.aurifolia.ginkgo.common.core.dto.ResultDTO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.aurifolia.ginkgo.common.core.constant.ResultCode.USER_REQUEST_PARAMETER_ERROR;

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
    public ResultDTO<Void> handle(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        return fieldError == null ? ResultDTO.from(USER_REQUEST_PARAMETER_ERROR, null)
                : ResultDTO.from(USER_REQUEST_PARAMETER_ERROR, fieldError.getDefaultMessage(), null);
    }
}
