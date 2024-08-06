package org.aurifolia.ginkgo.user.api.request;

import lombok.Data;
import org.aurifolia.common.api.annotation.FieldsNotAllEmpty;

/**
 * 用户信息-查询单条用户信息参数
 *
 * @author danpeng
 * @since 1.0
 */
@Data
@FieldsNotAllEmpty(fieldNames = {"id", "username", "email", "mobile"}, message = "{C0010000}")
public class UserQueryRequest {
    /**
     * 用户编号
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
}
