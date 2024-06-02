package org.aurifolia.ginkgo.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色-权限绑定关系
 *
 * @author danpeng
 * @since 1.0
 */
@Data
@TableName("role_perm_rela")
public class RolePermRela implements Serializable {
    @Serial
    private static final long serialVersionUID = 1184606119559322639L;

    /**
     * 绑定编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色编号
     */
    private Long roleId;
    /**
     * 权限编号
     */
    private Long permId;
}
