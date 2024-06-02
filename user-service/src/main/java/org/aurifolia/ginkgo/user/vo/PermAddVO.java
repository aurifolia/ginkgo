package org.aurifolia.ginkgo.user.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 添加权限请求参数
 *
 * @author danpeng
 * @since 1.0
 */
@Data
public class PermAddVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2400690315740214080L;

    /**
     * 权限名
     */
    @NotBlank(message = "{v0008}")
    private String permName;
    /**
     * 权限描述
     */
    @NotBlank(message = "{v0009}")
    private String permDesc;
}
