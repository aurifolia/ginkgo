package org.aurifolia.ginkgo.user.mapper;

import org.aurifolia.ginkgo.user.entity.UserRoleRela;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户-角色绑定关系相关的Mapper
 *
 * @author VNElinpe
 * @since 1.0
 */
@Mapper
public interface UserRoleRelaMapper extends BaseMapper<UserRoleRela> {

}
