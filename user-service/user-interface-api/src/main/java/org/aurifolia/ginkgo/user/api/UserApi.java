package org.aurifolia.ginkgo.user.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.aurifolia.common.api.dto.PageResponse;
import org.aurifolia.common.api.dto.Response;
import org.aurifolia.common.api.dto.SingleResponse;
import org.aurifolia.ginkgo.user.api.request.UserAddRequest;
import org.aurifolia.ginkgo.user.api.request.UserPageQueryRequest;
import org.aurifolia.ginkgo.user.api.request.UserQueryRequest;
import org.aurifolia.ginkgo.user.api.request.UserUpdateRequest;
import org.aurifolia.ginkgo.user.api.response.UserBaseInfoResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户相关的接口
 *
 * @author danpeng
 * @since 1.0
 */
@Validated
@RequestMapping("/v1/user")
public interface UserApi {
    /**
     * 查询单条用户信息
     *
     * @param queryRequest 查询参数
     * @return 用户信息
     */
    @GetMapping
    SingleResponse<UserBaseInfoResponse> query(@Validated UserQueryRequest queryRequest);

    /**
     * 分页查询用户信息
     *
     * @param queryRequest 查询参数
     * @return 用户信息
     */
    @PostMapping("/page")
    PageResponse<UserBaseInfoResponse> pageQuery(@RequestBody @Validated UserPageQueryRequest queryRequest);

    /**
     * 添加用户信息
     *
     * @param addRequest 用户信息
     * @return 操作结果
     */
    @PostMapping
    Response add(@RequestBody @Validated UserAddRequest addRequest);

    /**
     * 更新用户信息
     *
     * @param updateRequest 用户信息
     * @return 操作结果
     */
    @PutMapping
    Response update(@RequestBody @Validated UserUpdateRequest updateRequest);

    /**
     * 删除用户信息
     *
     * @param id 用户主键
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    Response delete(@PathVariable @NotNull(message = "{C00100017}") Long id);

    /**
     * 批量删除
     *
     * @param ids 用户主键列表
     * @return 操作结果
     */
    @PostMapping("/batch-delete")
    Response batchDelete(@RequestBody @NotEmpty(message = "{C00100020}") List<Long> ids);
}
