package org.aurifolia.ginkgo.uaa.service;

import org.aurifolia.ginkgo.uaa.entity.OAuth2RegisteredClientEntity;
import org.aurifolia.ginkgo.uaa.mapper.OAuth2RegisteredClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 使用数据库保存RegisteredClient
 *
 * @author VNElinpe
 * @since 1.0
 **/
@Service
public class CustomJdbcRegisteredClientRepository implements RegisteredClientRepository {
    @Autowired
    private OAuth2RegisteredClientMapper registeredClientMapper;

    @Override
    public void save(RegisteredClient registeredClient) {
        Assert.notNull(registeredClient, "registeredClient cannot be null");
        registeredClientMapper.saveOrUpdate(OAuth2RegisteredClientEntity.from(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        OAuth2RegisteredClientEntity registeredClientEntity = registeredClientMapper.findById(id);
        return registeredClientEntity == null ? null : registeredClientEntity.toRegisteredClient();
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        OAuth2RegisteredClientEntity registeredClientEntity = registeredClientMapper.findByClientId(clientId);
        return registeredClientEntity == null ? null : registeredClientEntity.toRegisteredClient();
    }
}
