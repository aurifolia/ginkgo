package org.aurifolia.ginkgo.user.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 添加角色请求参数
 *
 * @author danpeng
 * @since 1.0
 */
@Data
public class RoleAddVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -7234860254857936172L;

    /**
     * 角色名
     */
    @NotBlank(message = "{v0005}")
    private String roleName;
    /**
     * 角色描述
     */
    @NotBlank(message = "{v0006}")
    private String roleDesc;
}
