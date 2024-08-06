package org.aurifolia.common.api.dto;

import lombok.Data;

/**
 * 分页查询参数
 *
 * @author danpeng
 * @since 1.0
 */
@Data
public class PageQuery {
    public static final String DESC = "desc";
    public static final String ASC = "asc";

    /**
     * 当前页数
     */
    private Integer currPage = 1;
    /**
     * 每页条数
     */
    private Integer pageSize = 10;
    /**
     * 排序
     */
    private String orderBy;
    /**
     * 排序方向
     */
    private String order;
}
