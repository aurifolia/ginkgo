package org.aurifolia.ginkgo.user.service.impl;

import org.aurifolia.ginkgo.user.entity.PermInfo;
import org.aurifolia.ginkgo.user.mapper.PermInfoMapper;
import org.aurifolia.ginkgo.user.service.IPermInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 权限相关
 *
 * @author VNElinpe
 * @since 1.0
 */
@Service
public class PermInfoServiceImpl extends ServiceImpl<PermInfoMapper, PermInfo> implements IPermInfoService {

}
