package org.aurifolia.ginkgo.uaa.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.aurifolia.ginkgo.uaa.entity.OAuth2RegisteredClientEntity;

/**
 * TODO
 *
 * @author VNElinpe
 * @since 1.0
 **/
@Mapper
public interface OAuth2RegisteredClientMapper {
    /**
     * 根据id查询OAuth2RegisteredClientEntity
     *
     * @param id id
     * @return OAuth2RegisteredClientEntity
     */
    OAuth2RegisteredClientEntity findById(String id);

    /**
     * 根据clientId查询OAuth2RegisteredClientEntity
     *
     * @param clientId clientId
     * @return OAuth2RegisteredClientEntity
     */
    OAuth2RegisteredClientEntity findByClientId(String clientId);

    /**
     * 保存或更新OAuth2RegisteredClientEntity
     *
     * @param registeredClientEntity OAuth2RegisteredClientEntity
     * @return 受影响的行数
     */
    int saveOrUpdate(OAuth2RegisteredClientEntity registeredClientEntity);
}
