package org.aurifolia.ginkgo.user.controller;

import org.aurifolia.ginkgo.common.core.annotation.ParameterExtends;
import org.aurifolia.ginkgo.common.core.dto.ResultDTO;
import org.aurifolia.ginkgo.common.core.exception.CommonException;
import org.aurifolia.ginkgo.user.dto.PermInfoDTO;
import org.aurifolia.ginkgo.user.entity.PermInfo;
import org.aurifolia.ginkgo.user.service.IPermInfoService;
import org.aurifolia.ginkgo.user.vo.PermAddVO;
import org.aurifolia.ginkgo.user.vo.PermUpdateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.aurifolia.ginkgo.common.core.constant.ResultCode.PERM_NOT_EXISTS;

/**
 * 权限管理
 *
 * @author danpeng
 * @since 1.0
 */
@RestController
@RequestMapping("/perm")
@ParameterExtends(transformTimeZone = true)
public class PermInfoController {
    private final IPermInfoService permInfoService;

    public PermInfoController(IPermInfoService permInfoService) {
        this.permInfoService = permInfoService;
    }

    /**
     * 添加权限基本信息
     *
     * @param permAddVO 权限基本信息
     * @return 操作结果
     */
    @PutMapping
    public ResultDTO<Void> addPerm(@RequestBody @Validated PermAddVO permAddVO) {
        PermInfo permInfo = new PermInfo();
        BeanUtils.copyProperties(permAddVO, permInfo);
        return permInfoService.save(permInfo) ? ResultDTO.success() : ResultDTO.fail();
    }

    /**
     * 修改权限基本信息
     *
     * @param permUpdateVO 权限基本信息
     * @return 操作结果
     */
    @PostMapping
    public ResultDTO<Void> updatePerm(@RequestBody @Validated PermUpdateVO permUpdateVO) {
        PermInfo permInfo = new PermInfo();
        BeanUtils.copyProperties(permUpdateVO, permInfo);
        return permInfoService.updateById(permInfo) ? ResultDTO.success() : ResultDTO.fail();
    }

    /**
     * 删除权限
     *
     * @param id 权限编号
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    public ResultDTO<Void> deletePerm(@PathVariable Long id) {
        return permInfoService.removeById(id) ? ResultDTO.success() : ResultDTO.fail();
    }

    /**
     * 获取权限信息
     *
     * @param id 权限编号
     * @return 权限信息
     */
    @GetMapping("/{id}")
    public ResultDTO<PermInfoDTO> getPerm(@PathVariable Long id) {
        PermInfo permInfo = permInfoService.getById(id);
        if (permInfo == null) {
            throw new CommonException(PERM_NOT_EXISTS);
        }
        PermInfoDTO permInfoDTO = new PermInfoDTO();
        BeanUtils.copyProperties(permInfo, permInfoDTO);
        return ResultDTO.success(permInfoDTO);
    }
}
