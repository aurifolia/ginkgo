package org.aurifolia.ginkgo.user.controller;

import org.aurifolia.ginkgo.common.core.dto.ResultDTO;
import org.aurifolia.ginkgo.common.core.exception.CommonException;
import org.aurifolia.ginkgo.user.dto.RoleInfoDTO;
import org.aurifolia.ginkgo.user.entity.RoleInfo;
import org.aurifolia.ginkgo.user.service.IRoleInfoService;
import org.aurifolia.ginkgo.user.vo.RoleAddVO;
import org.aurifolia.ginkgo.user.vo.RoleUpdateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.aurifolia.ginkgo.common.core.constant.ResultCode.ROLE_NOT_EXISTS;

/**
 * 角色管理
 *
 * @author danpeng
 * @since 1.0
 */
@RestController
@RequestMapping("/role")
public class RoleInfoController {
    private final IRoleInfoService roleInfoService;

    public RoleInfoController(IRoleInfoService roleInfoService) {
        this.roleInfoService = roleInfoService;
    }

    /**
     * 添加角色
     *
     * @param roleAddVO 角色基本信息
     * @return 操作结果
     */
    @PutMapping
    public ResultDTO<Void> addRole(@RequestBody @Validated RoleAddVO roleAddVO) {
        RoleInfo roleInfo = new RoleInfo();
        BeanUtils.copyProperties(roleAddVO, roleInfo);
        return roleInfoService.save(roleInfo) ? ResultDTO.success() : ResultDTO.fail();
    }

    /**
     * 更新角色
     *
     * @param roleUpdateVO 请求参数
     * @return 操作结果
     */
    @PostMapping
    public ResultDTO<Void> updateRole(@RequestBody @Validated RoleUpdateVO roleUpdateVO) {
        RoleInfo roleInfo = new RoleInfo();
        BeanUtils.copyProperties(roleUpdateVO, roleInfo);
        return roleInfoService.updateById(roleInfo) ? ResultDTO.success() : ResultDTO.fail();
    }

    /**
     * 删除角色
     *
     * @param id 角色编号
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    public ResultDTO<Void> deleteRole(@PathVariable Long id) {
        return roleInfoService.removeById(id) ? ResultDTO.success() : ResultDTO.fail();
    }

    /**
     * 查询角色
     *
     * @param id 角色编号
     * @return 角色基本信息
     */
    @GetMapping("/{id}")
    public ResultDTO<RoleInfoDTO> getRole(@PathVariable Long id) {
        RoleInfo roleInfo = roleInfoService.getById(id);
        if (roleInfo == null) {
            throw new CommonException(ROLE_NOT_EXISTS);
        }
        RoleInfoDTO roleInfoDTO = new RoleInfoDTO();
        BeanUtils.copyProperties(roleInfo, roleInfoDTO);
        return ResultDTO.success(roleInfoDTO);
    }
}
