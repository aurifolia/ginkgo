package org.aurifolia.ginkgo.user.mapper;

import org.aurifolia.ginkgo.common.security.entity.Principal;
import org.aurifolia.ginkgo.user.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户基本信息相关的Mapper
 *
 * @author VNElinpe
 * @since 1.0
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    /**
     * 根据用户名查询认证授权信息
     *
     * @param username 用户名
     * @return Principal
     */
    Principal loadUserByUsername(String username);
}
