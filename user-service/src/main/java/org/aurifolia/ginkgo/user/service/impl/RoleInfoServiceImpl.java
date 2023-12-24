package org.aurifolia.ginkgo.user.service.impl;

import org.aurifolia.ginkgo.user.entity.RoleInfo;
import org.aurifolia.ginkgo.user.mapper.RoleInfoMapper;
import org.aurifolia.ginkgo.user.service.IRoleInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色相关
 *
 * @author VNElinpe
 * @since 1.0
 */
@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements IRoleInfoService {

}
