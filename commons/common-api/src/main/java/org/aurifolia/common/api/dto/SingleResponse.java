package org.aurifolia.common.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.aurifolia.common.api.enums.ResponseCodeEnum;

/**
 * 单个数据反参
 *
 * @author danpeng
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SingleResponse<T> extends Response {
    /**
     * 数据
     */
    private T data;

    /**
     * 构造响应信息
     *
     * @param data 数据
     * @return 响应信息
     */
    public static <T> Response of(T data) {
        SingleResponse<T> response = new SingleResponse<>();
        ResponseCodeEnum codeEnum = ResponseCodeEnum.SUCCESS;
        response.setCode(codeEnum.getCode());
        response.setMessage(codeEnum.getDefaultMessage());
        response.setData(data);
        return response;
    }
}
