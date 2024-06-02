package org.aurifolia.ginkgo.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.aurifolia.ginkgo.user.entity.RoleInfo;
import org.aurifolia.ginkgo.user.mapper.RoleInfoMapper;
import org.aurifolia.ginkgo.user.service.IRoleInfoService;
import org.springframework.stereotype.Service;

/**
 * 角色基本信息相关
 *
 * @author danpeng
 * @since 1.0
 */
@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements IRoleInfoService {

}
