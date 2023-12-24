package org.aurifolia.ginkgo.user.service.impl;

import org.aurifolia.ginkgo.user.entity.UserInfo;
import org.aurifolia.ginkgo.user.mapper.UserInfoMapper;
import org.aurifolia.ginkgo.user.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户相关
 *
 * @author VNElinpe
 * @since 1.0
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements IUserInfoService, UserDetailsService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userInfoMapper.loadUserByUsername(username);
    }
}
