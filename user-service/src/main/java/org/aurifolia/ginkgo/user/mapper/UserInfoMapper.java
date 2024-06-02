package org.aurifolia.ginkgo.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.aurifolia.ginkgo.user.entity.UserInfo;

/**
 * 用户基本信息相关
 *
 * @author danpeng
 * @since 1.0
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
