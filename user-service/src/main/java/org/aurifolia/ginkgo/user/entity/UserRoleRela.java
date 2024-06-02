package org.aurifolia.ginkgo.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户-角色绑定关系
 *
 * @author danpeng
 * @since 1.0
 */
@Data
@TableName("user_role_rela")
public class UserRoleRela implements Serializable {
    @Serial
    private static final long serialVersionUID = 2891002127922308773L;

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
