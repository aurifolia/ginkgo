package org.aurifolia.ginkgo.user.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 添加用户请求参数
 *
 * @author danpeng
 * @since 1.0
 */
@Data
public class UserAddVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3987372278720870424L;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;
    /**
     * 密码(单向加密后的密码)
     */
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * 用户昵称
     */
    @NotBlank(message = "用户昵称不能为空")
    private String nickname;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 生日
     */
    private LocalDate birthday;
    /**
     * 简介
     */
    private String profile;
}
