package org.aurifolia.ginkgo.user.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.aurifolia.common.api.annotation.FieldsNotAllEmpty;
import org.aurifolia.common.api.annotation.VerifiedBy;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static org.aurifolia.common.api.constant.CommonConstant.DEFAULT_DATE_FORMAT;

/**
 * 用户信息-新增参数
 *
 * @author danpeng
 * @since 1.0
 */
@Data
@FieldsNotAllEmpty(fieldNames = {"email", "mobile"}, message = "{C0010003}")
public class UserAddRequest {
    /**
     * 用户名
     */
    @NotEmpty(message = "{C0010002}")
    @Length(min = 1, max = 32, message = "{C0010008}")
    private String username;
    /**
     * 用户昵称
     */
    @Length(min = 1, max = 32, message = "{C0010009}")
    private String nickname;
    /**
     * 用户头像
     */
    @Length(max = 255, message = "{C0010014}")
    private String avatar;
    /**
     * 用户简介
     */
    @Length(max = 128, message = "{C0010013}")
    private String profile;
    /**
     * 生日
     */
    @JsonFormat(pattern = DEFAULT_DATE_FORMAT)
    private LocalDate birthday;
    /**
     * 电子邮箱
     */
    @Email(message = "{C0010004}")
    @Length(max = 128, message = "{C0010010}")
    private String email;
    /**
     * 手机号
     */
    @VerifiedBy(staticMethodFullName = "org.aurifolia.ginkgo.user.api.util.ValidationUtil.validateMobileNumber",
            message = "{C0010011}")
    @Length(max = 32, message = "{C0010012}")
    private String mobile;
    /**
     * 验证码ID
     */
    @NotEmpty(message = "{C0010006}")
    @Length(max = 32, message = "{C0010015}")
    private String verifyId;
    /**
     * 验证码
     */
    @NotEmpty(message = "{C0010007}")
    @Length(max = 16, message = "{C0010016}")
    private String verifyCode;
}
