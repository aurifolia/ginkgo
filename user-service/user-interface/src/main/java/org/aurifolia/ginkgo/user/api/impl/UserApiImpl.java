package org.aurifolia.ginkgo.user.api.impl;

import org.aurifolia.common.api.dto.PageResponse;
import org.aurifolia.common.api.dto.Response;
import org.aurifolia.common.api.dto.SingleResponse;
import org.aurifolia.ginkgo.user.api.UserApi;
import org.aurifolia.ginkgo.user.api.request.UserAddRequest;
import org.aurifolia.ginkgo.user.api.request.UserPageQueryRequest;
import org.aurifolia.ginkgo.user.api.request.UserQueryRequest;
import org.aurifolia.ginkgo.user.api.request.UserUpdateRequest;
import org.aurifolia.ginkgo.user.api.response.UserBaseInfoResponse;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户相关的接口
 *
 * @author danpeng
 * @since 1.0
 */
@RestController
public class UserApiImpl implements UserApi {
    @Override
    public SingleResponse<UserBaseInfoResponse> query(UserQueryRequest queryRequest) {
        return null;
    }

    @Override
    public PageResponse<UserBaseInfoResponse> pageQuery(UserPageQueryRequest queryRequest) {
        return null;
    }

    @Override
    public Response add(UserAddRequest addRequest) {
        return null;
    }

    @Override
    public Response update(UserUpdateRequest updateRequest) {
        return null;
    }

    @Override
    public Response delete(Long id) {
        return null;
    }

    @Override
    public Response batchDelete(List<Long> ids) {
        return null;
    }
}
