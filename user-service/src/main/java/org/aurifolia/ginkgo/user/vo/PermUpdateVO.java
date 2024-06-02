package org.aurifolia.ginkgo.user.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 更新权限请求参数
 *
 * @author danpeng
 * @since 1.0
 */
@Data
public class PermUpdateVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 136840699660086277L;

    /**
     * 权限编号
     */
    @NotNull(message = "{v0010}")
    private Long id;
    /**
     * 权限名
     */
    private String permName;
    /**
     * 权限描述
     */
    private String permDesc;
}
