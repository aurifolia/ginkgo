package org.aurifolia.ginkgo.user.api.response;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户基本信息
 *
 * @author danpeng
 * @since 1.0
 */
@Data
public class UserBaseInfoResponse {
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
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

}
