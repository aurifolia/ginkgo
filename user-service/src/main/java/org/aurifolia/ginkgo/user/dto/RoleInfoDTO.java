package org.aurifolia.ginkgo.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import static org.aurifolia.ginkgo.common.core.constant.CommonConstant.DEFAULT_DATE_TIME_PATTERN;

/**
 * 角色基本信息
 *
 * @author danpeng
 * @since 1.0
 */
@Data
public class RoleInfoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -1669628797250346077L;

    /**
     * 角色编号
     */
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
    @JsonFormat(pattern = DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime updateTime;
}
