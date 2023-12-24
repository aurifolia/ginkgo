package org.aurifolia.ginkgo.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色基本信息
 *
 * @author VNElinpe
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("role_info")
public class RoleInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 角色主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDesc;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
