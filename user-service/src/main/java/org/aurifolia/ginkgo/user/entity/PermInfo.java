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
 * 权限基本信息
 *
 * @author VNElinpe
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("perm_info")
public class PermInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 权限编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 权限名
     */
    private String permName;
    /**
     * 权限描述
     */
    private String permDesc;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
