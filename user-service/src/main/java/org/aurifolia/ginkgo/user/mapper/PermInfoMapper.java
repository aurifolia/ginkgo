package org.aurifolia.ginkgo.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.aurifolia.ginkgo.user.entity.PermInfo;

/**
 * 权限基本信息相关
 *
 * @author danpeng
 * @since 1.0
 */
@Mapper
public interface PermInfoMapper extends BaseMapper<PermInfo> {

}
