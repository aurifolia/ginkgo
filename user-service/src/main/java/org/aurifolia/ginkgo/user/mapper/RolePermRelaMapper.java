package org.aurifolia.ginkgo.user.mapper;

import org.aurifolia.ginkgo.user.entity.RolePermRela;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色-权限绑定关系相关的Mapper
 *
 * @author VNElinpe
 * @since 1.0
 */
@Mapper
public interface RolePermRelaMapper extends BaseMapper<RolePermRela> {

}
