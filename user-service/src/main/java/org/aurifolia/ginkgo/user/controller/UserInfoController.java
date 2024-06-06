package org.aurifolia.ginkgo.user.controller;

import org.aurifolia.ginkgo.common.core.annotation.ParameterExtends;
import org.aurifolia.ginkgo.common.core.dto.ResultDTO;
import org.aurifolia.ginkgo.common.core.exception.CommonException;
import org.aurifolia.ginkgo.user.dto.UserInfoDTO;
import org.aurifolia.ginkgo.user.entity.UserInfo;
import org.aurifolia.ginkgo.user.service.IUserInfoService;
import org.aurifolia.ginkgo.user.vo.UserAddVO;
import org.aurifolia.ginkgo.user.vo.UserUpdateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.aurifolia.ginkgo.common.core.constant.ResultCode.USER_NOT_EXISTS;

/**
 * 用户管理
 *
 * @author danpeng
 * @since 1.0
 */
@RestController
@RequestMapping("/user")
@ParameterExtends(transformTimeZone = true)
public class UserInfoController {
    private final IUserInfoService userInfoService;

    public UserInfoController(IUserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    /**
     * 添加用户
     *
     * @param userAddVO 用户基本信息
     * @return 操作结果
     */
    @PutMapping
    public ResultDTO<Void> addUser(@RequestBody @Validated UserAddVO userAddVO) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userAddVO, userInfo);
        return userInfoService.save(userInfo) ? ResultDTO.success() : ResultDTO.fail();
    }

    /**
     * 修改用户
     *
     * @param userUpdateVO 用户基本信息
     * @return 操作结果
     */
    @PostMapping
    public ResultDTO<Void> updateUser(@RequestBody @Validated UserUpdateVO userUpdateVO) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userUpdateVO, userInfo);
        return userInfoService.updateById(userInfo) ? ResultDTO.success() : ResultDTO.fail();
    }

    /**
     * 删除用户
     *
     * @param id 用户编号
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    public ResultDTO<Void> deleteUser(@PathVariable Long id) {
        return userInfoService.removeById(id) ? ResultDTO.success() : ResultDTO.fail();
    }

    /**
     * 获取用户信息
     *
     * @param id 用户编号
     * @return 用户基本信息
     */
    @GetMapping("/{id}")
    public ResultDTO<UserInfoDTO> getUser(@PathVariable Long id) {
        UserInfo userInfo = userInfoService.getById(id);
        if (userInfo == null) {
            throw new CommonException(USER_NOT_EXISTS);
        }
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(userInfo, userInfoDTO);
        return ResultDTO.success(userInfoDTO);
    }
}
