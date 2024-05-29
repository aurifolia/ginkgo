package org.aurifolia.ginkgo.common.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * 分页模型
 *
 * @author danpeng
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class PageDTO<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 2708723333993301202L;
    private static final PageDTO<?> EMPTY = new PageDTO<>(0, 0, 0, 0L, Collections.emptyList());

    /**
     * 当前页数
     */
    private Integer curPage;
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
     * 当前页的数据列表
     */
    private Collection<T> items;

    /**
     * 从全量集合里构造出分页后的数据
     *
     * @param allItems 全量集合
     * @param curPage 当前页数
     * @param pageSize 每页条数
     * @return 分页数据
     */
    public static PageDTO<?> from(Collection<?> allItems, Integer curPage, Integer pageSize) {
        if (CollectionUtils.isEmpty(allItems)) {
            return EMPTY;
        }
        int skip = (curPage - 1) * pageSize;
        int totalItems = allItems.size();
        if (totalItems <= skip) {
            return EMPTY;
        }
        return new PageDTO<>(curPage, pageSize, Math.ceilDiv(totalItems, pageSize), (long) totalItems,
                allItems.stream().skip(skip).limit(pageSize).toList());
    }
}
