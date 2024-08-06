package org.aurifolia.ginkgo.user.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.aurifolia.common.api.annotation.FieldsNotAllEmpty;
import org.aurifolia.common.api.dto.PageQuery;

/**
 * 用户信息-分页查询参数
 *
 * @author danpeng
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@FieldsNotAllEmpty(fieldNames = {"keyword"}, message = "{C0010001}")
public class UserPageQueryRequest extends PageQuery {
    /**
     * 搜索关键字
     */
    private String keyword;
}
