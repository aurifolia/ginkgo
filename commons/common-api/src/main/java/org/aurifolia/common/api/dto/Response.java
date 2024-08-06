package org.aurifolia.common.api.dto;

import lombok.Data;
import org.aurifolia.common.api.enums.ResponseCodeEnum;

/**
 * 返回参数
 *
 * @author danpeng
 * @since 1.0
 */
@Data
public class Response {
    /**
     * 响应码
     */
    private String code;
    /**
     * 响应信息
     */
    private String message;

    /**
     * 构造成功状态下的响应信息
     *
     * @return 响应信息
     */
    public static Response success() {
        return of(ResponseCodeEnum.SUCCESS);
    }

    /**
     * 构造失败状态下的响应信息
     *
     * @return 响应信息
     */
    public static Response fail() {
        return of(ResponseCodeEnum.SERVICE_ERROR);
    }

    /**
     * 构造响应信息
     *
     * @param codeEnum 响应码枚举
     * @return 响应信息
     */
    public static Response of(ResponseCodeEnum codeEnum) {
        return of(codeEnum.getCode(), codeEnum.getDefaultMessage());
    }

    /**
     * 构造响应参数
     *
     * @param code    响应码
     * @param message 响应信息
     * @return 响应参数
     */
    public static Response of(String code, String message) {
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }


}
