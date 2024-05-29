package org.aurifolia.ginkgo.common.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

import static org.aurifolia.ginkgo.common.core.constant.ResultCode.SERVICE_ERROR;
import static org.aurifolia.ginkgo.common.core.constant.ResultCode.SUCCESS;

/**
 * 通用返回模型
 *
 * @author danpeng
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -2173124097803847529L;

    /**
     * 返回码
     */
    private String code;
    /**
     * 返回码描述
     */
    private String message;
    /**
     * 数据本身
     */
    private T data;

    /**
     * 获取成功时的返回模型
     *
     * @param <T> 泛型
     * @return 返回模型
     */
    public static <T> ResultDTO<T> success() {
        return from(SUCCESS, null);
    }

    /**
     * 获取成功时的返回模型
     *
     * @param data 数据本身
     * @param <T>  泛型
     * @return 返回模型
     */
    public static <T> ResultDTO<T> success(T data) {
        return from(SUCCESS, data);
    }

    /**
     * 获取失败时的返回模型
     *
     * @return 返回模型
     */
    public static ResultDTO<Void> fail() {
        return from(SERVICE_ERROR, null);
    }

    /**
     * 获取返回模型
     *
     * @param code 返回码
     * @param data 数据本身
     * @param <T>  泛型
     * @return 返回模型
     */
    public static <T> ResultDTO<T> from(String code, T data) {
        return from(code, null, data);
    }

    /**
     * 获取返回模型
     *
     * @param code    返回码
     * @param message 返回码描述信息
     * @param data    数据本身
     * @param <T>     泛型
     * @return 返回模型
     */
    public static <T> ResultDTO<T> from(String code, String message, T data) {
        return ResultDTO.<T>builder()
                .code(code)
                .message(message)
                .data(data).build();
    }
}
