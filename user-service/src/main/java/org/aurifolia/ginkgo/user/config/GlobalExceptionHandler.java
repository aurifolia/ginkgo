package org.aurifolia.ginkgo.user.config;

import org.aurifolia.ginkgo.commons.dto.ResultDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

/**
 * 全局异常处理
 *
 * @author VNElinpe
 * @since 1.0
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理异常
     *
     * @param exception exception
     * @param handlerMethod handlerMethod
     * @return ResultDTO
     */
    @ExceptionHandler(Exception.class)
    public ResultDTO<Void> handler(Exception exception, HandlerMethod handlerMethod) {
        System.out.println(Thread.currentThread().getName());
        return ResultDTO.fail();
    }
}
