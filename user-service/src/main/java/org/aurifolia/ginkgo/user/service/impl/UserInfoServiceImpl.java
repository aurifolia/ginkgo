package org.aurifolia.ginkgo.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.aurifolia.ginkgo.user.entity.UserInfo;
import org.aurifolia.ginkgo.user.mapper.UserInfoMapper;
import org.aurifolia.ginkgo.user.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * 用户基本信息相关
 *
 * @author danpeng
 * @since 1.0
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
