package org.aurifolia.ginkgo.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户基本信息
 *
 * @author VNElinpe
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_info")
public class UserInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 用户编号
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 用户别名
     */
    private String surname;
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
     * 锁定标识(0=未锁定, 1=锁定)
     */
    @TableField("is_locked")
    private Boolean locked;
    /**
     * 删除标识(0=未删除, 1=删除)
     */
    @TableField("is_deleted")
    private Boolean deleted;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
