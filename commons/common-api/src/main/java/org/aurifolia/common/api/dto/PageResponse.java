package org.aurifolia.common.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.aurifolia.common.api.enums.ResponseCodeEnum;

import java.util.Collection;

/**
 * 分页响应信息
 *
 * @author danpeng
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageResponse<T> extends MultiResponse<T> {
    /**
     * 当前页数
     */
    private Integer currPage;
    /**
     * 每页条数
     */
    private Integer pageSize;
    /**
     * 总页数
     */
    private Integer totalPages;
    /**
     * 总条数
     */
    private Long totalItems;

    /**
     * 构造响应信息
     *
     * @param currPage 当前页数
     * @param pageSize 每页条数
     * @param totalPages 总条数
     * @param totalItems 总页数
     * @param data 分页数据
     * @return 响应信息
     * @param <T> 类型
     */
    public static <T> PageResponse<T> of(int currPage, int pageSize, int totalPages,
                                         long totalItems, Collection<T> data) {
        PageResponse<T> response = new PageResponse<>();
        ResponseCodeEnum codeEnum = ResponseCodeEnum.SUCCESS;
        response.setCode(codeEnum.getCode());
        response.setMessage(codeEnum.getDefaultMessage());
        response.setCurrPage(currPage);
        response.setPageSize(pageSize);
        response.setTotalPages(totalPages);
        response.setTotalItems(totalItems);
        response.setData(data);
        return response;
    }
}
