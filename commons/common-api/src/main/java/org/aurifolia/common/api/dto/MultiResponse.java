package org.aurifolia.common.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.aurifolia.common.api.enums.ResponseCodeEnum;

import java.util.Collection;

/**
 * 多个数据返参
 *
 * @author danpeng
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MultiResponse<T> extends Response {
    /**
     * 数据
     */
    private Collection<T> data;

    public static <T> MultiResponse<T> of(Collection<T> data) {
        MultiResponse<T> response = new MultiResponse<>();
        ResponseCodeEnum codeEnum = ResponseCodeEnum.SUCCESS;
        response.setCode(codeEnum.getCode());
        response.setMessage(codeEnum.getDefaultMessage());
        response.setData(data);
        return response;
    }
}
