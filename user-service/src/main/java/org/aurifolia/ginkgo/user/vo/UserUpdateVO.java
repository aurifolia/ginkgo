package org.aurifolia.ginkgo.user.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 更新用户请求参数
 *
 * @author danpeng
 * @since 1.0
 */
@Data
public class UserUpdateVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1136930865199146827L;

    /**
     * 用户编号
     */
    @NotNull(message = "{v0004}")
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码(单向加密后的密码)
     */
    private String password;
    /**
     * 用户昵称
     */
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
    /**
     * 锁定标识
     */
    private Boolean isLocked;
}
