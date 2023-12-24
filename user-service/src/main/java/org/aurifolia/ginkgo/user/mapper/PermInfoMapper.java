package org.aurifolia.ginkgo.user.mapper;

import org.aurifolia.ginkgo.user.entity.PermInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 权限基本信息相关的Mapper
 *
 * @author VNElinpe
 * @since 1.0
 */
@Mapper
public interface PermInfoMapper extends BaseMapper<PermInfo> {

}
