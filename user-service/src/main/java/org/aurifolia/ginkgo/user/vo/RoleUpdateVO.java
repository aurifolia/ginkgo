package org.aurifolia.ginkgo.user.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 更新角色请求参数
 *
 * @author danpeng
 * @since 1.0
 */
@Data
public class RoleUpdateVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2721453880992966397L;

    /**
     * 角色编号
     */
    @NotNull(message = "角色编号不能为空")
    private Long id;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDesc;
}
