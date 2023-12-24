package org.aurifolia.ginkgo.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aurifolia.ginkgo.commons.constant.ResultCode;

/**
 * 返回模型
 *
 * @author VNElinpe
 * @since 1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO<T> {
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
     * @return 返回模型
     * @param <T> 泛型
     */
    public static <T> ResultDTO<T> success() {
        return from(ResultCode.SUCCESS, null);
    }

    /**
     * 获取成功时的返回模型
     *
     * @param data 数据本身
     * @return 返回模型
     * @param <T> 泛型
     */
    public static <T> ResultDTO<T> success(T data) {
        return from(ResultCode.SUCCESS, data);
    }

    /**
     * 获取失败时的返回模型
     *
     * @return 返回模型
     */
    public static ResultDTO<Void> fail() {
        return from(ResultCode.SERVICE_ERROR, null);
    }

    /**
     * 获取返回模型
     *
     * @param code 返回码
     * @param data 数据本身
     * @return 返回模型
     * @param <T> 泛型
     */
    public static <T> ResultDTO<T> from(String code, T data) {
        return from(code, null, data);
    }

    /**
     * 获取返回模型
     *
     * @param code 返回码
     * @param message 返回码描述信息
     * @param data 数据本身
     * @return 返回模型
     * @param <T> 泛型
     */
    public static <T> ResultDTO<T> from(String code, String message, T data) {
        return ResultDTO.<T>builder()
                .code(code)
                .message(message)
                .data(data).build();
    }
}
