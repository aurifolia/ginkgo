package org.aurifolia.ginkgo.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import lombok.*;

/**
 * 用户-角色绑定关系
 *
 * @author VNElinpe
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_role_rela")
public class UserRoleRela implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 绑定编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 角色编号
     */
    private Long roleId;
}
