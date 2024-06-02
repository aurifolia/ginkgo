package org.aurifolia.ginkgo.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.aurifolia.ginkgo.common.core.constant.CommonConstant.DEFAULT_DATE_TIME_PATTERN;

/**
 * 用户基本信息
 *
 * @author danpeng
 * @since 1.0
 */
@Data
public class UserInfoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3898808211730483110L;

    /**
     * 用户编号
     */
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
    /**
     * 删除标识
     */
    private Boolean isDeleted;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime updateTime;
}
