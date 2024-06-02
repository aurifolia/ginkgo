package org.aurifolia.ginkgo.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import static org.aurifolia.ginkgo.common.core.constant.CommonConstant.DEFAULT_DATE_TIME_PATTERN;

/**
 * 权限基本信息
 *
 * @author danpeng
 * @since 1.0
 */
@Data
public class PermInfoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -7702024674201461453L;

    /**
     * 权限编号
     */
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
    @JsonFormat(pattern = DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime updateTime;
}
