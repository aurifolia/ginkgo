package org.aurifolia.ginkgo.user.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.aurifolia.common.api.annotation.FieldsAllNotEmpty;
import org.aurifolia.common.api.annotation.FieldsAllNotEmptyThenAllNotEmpty;
import org.aurifolia.common.api.annotation.FieldsNotAllEmpty;
import org.aurifolia.common.api.annotation.FieldsNotAllEmptyThenAllNotEmpty;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

import static org.aurifolia.common.api.constant.CommonConstant.DEFAULT_DATE_FORMAT;

/**
 * 用户信息-更新参数
 *
 * @author danpeng
 * @since 1.0
 */
@Data
@FieldsNotAllEmpty(fieldNames = {"username", "nickname", "avatar", "birthday", "email", "mobile"},
        message = "C00100019")
@FieldsNotAllEmptyThenAllNotEmpty(condition = @FieldsNotAllEmpty(fieldNames = {"username", "email", "mobile"}),
        rela = @FieldsAllNotEmpty(fieldNames = {"verifyId", "verifyCode"}), message = "{C00100018}")
public class UserUpdateRequest {
    /**
     * 用户编号
     */
    @NotNull(message = "{C00100017}")
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 生日
     */
    @JsonFormat(pattern = DEFAULT_DATE_FORMAT)
    private LocalDate birthday;
    /**
     * 验证码ID
     */
    @Length(max = 32, message = "{C0010015}")
    private String verifyId;
    /**
     * 验证码
     */
    @Length(max = 16, message = "{C0010016}")
    private String verifyCode;
}
