package org.aurifolia.ginkgo.uaa.service;

import jakarta.annotation.Resource;
import org.aurifolia.ginkgo.uaa.entity.OAuth2AuthorizationEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 使用Redis存储oauth2认证信息
 *
 * @author VNElinpe
 * @since 1.0
 **/
@Service
public class RedisOAuth2AuthorizationService implements OAuth2AuthorizationService {
    private static final String OAUTH2_AUTHORIZATION_KEY_PATTERN = "oauth2:auth:data:%s";
    private static final String OAUTH2_AUTHORIZATION_INDEX_KEY_PATTERN = "oauth2:auth:idx:%s:%s";
    private static final int DEFAULT_OAUTH2_AUTHORIZATION_INDEX_SIZE = 16;
    private static final long DEFAULT_OAUTH2_AUTHORIZATION_TTL_MS = 3600_000;

    @Resource(name = "redisTemplate4Cache")
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(OAuth2Authorization authorization) {
        Map<String, Long> oAuth2AuthorizationIndexes = getOAuth2AuthorizationIndexes(authorization, false);
        Long maxTtlMs = oAuth2AuthorizationIndexes.values().stream().filter(Objects::nonNull)
                .max(Long::compareTo).orElse(DEFAULT_OAUTH2_AUTHORIZATION_TTL_MS);
        oAuth2AuthorizationIndexes.forEach((k, v) -> redisTemplate.opsForValue()
                .set(k, authorization.getId(), v == null ? maxTtlMs : v, TimeUnit.MILLISECONDS));
        redisTemplate.opsForValue().set(getOAuth2AuthorizationKey(authorization),
                OAuth2AuthorizationEntity.from(authorization), maxTtlMs, TimeUnit.MILLISECONDS);
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        redisTemplate.delete(getOAuth2AuthorizationKey(authorization));
        getOAuth2AuthorizationIndexes(authorization, true).keySet().forEach(redisTemplate::delete);
    }

    @Override
    public OAuth2Authorization findById(String id) {
        return (OAuth2Authorization) redisTemplate.opsForValue().get(getOAuth2AuthorizationKey(id));
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        List<String> indexKeys = new ArrayList<>(DEFAULT_OAUTH2_AUTHORIZATION_INDEX_SIZE);
        if (tokenType != null) {
            indexKeys.add(getOAuth2AuthorizationIndexKey(tokenType.getValue(), token));
        }
        else {
            indexKeys.add(getOAuth2AuthorizationIndexKey(OAuth2ParameterNames.STATE, token));
            indexKeys.add(getOAuth2AuthorizationIndexKey(OAuth2ParameterNames.CODE, token));
            indexKeys.add(getOAuth2AuthorizationIndexKey(OAuth2TokenType.ACCESS_TOKEN.getValue(), token));
            indexKeys.add(getOAuth2AuthorizationIndexKey(OidcParameterNames.ID_TOKEN, token));
            indexKeys.add(getOAuth2AuthorizationIndexKey(OAuth2TokenType.REFRESH_TOKEN.getValue(), token));
            indexKeys.add(getOAuth2AuthorizationIndexKey(OAuth2ParameterNames.USER_CODE, token));
            indexKeys.add(getOAuth2AuthorizationIndexKey(OAuth2ParameterNames.DEVICE_CODE, token));
        }
        String id = null;
        for (String indexKey : indexKeys) {
            id = (String) redisTemplate.opsForValue().get(indexKey);
            if (id != null) {
                break;
            }
        }
        if (id == null) {
            return null;
        }
        OAuth2AuthorizationEntity oAuth2AuthorizationEntity = (OAuth2AuthorizationEntity) redisTemplate
                .opsForValue().get(getOAuth2AuthorizationKey(id));
        if (oAuth2AuthorizationEntity == null) {
            return null;
        }
        return oAuth2AuthorizationEntity.toOAuth2Authorization();
    }

    private String getOAuth2AuthorizationKey(String id) {
        return String.format(OAUTH2_AUTHORIZATION_KEY_PATTERN, id);
    }

    private String getOAuth2AuthorizationKey(OAuth2Authorization oAuth2Authorization) {
        return getOAuth2AuthorizationKey(oAuth2Authorization.getId());
    }

    private String getOAuth2AuthorizationIndexKey(String tokenType, String token) {
        return String.format(OAUTH2_AUTHORIZATION_INDEX_KEY_PATTERN, tokenType, token);
    }

    private Map<String, Long> getOAuth2AuthorizationIndexes(OAuth2Authorization oAuth2Authorization,
                                                            boolean includeInactive) {
        Instant now = Instant.now();
        Map<String, Long> indexes = new HashMap<>(DEFAULT_OAUTH2_AUTHORIZATION_INDEX_SIZE);
        String state = oAuth2Authorization.getAttribute(OAuth2ParameterNames.STATE);
        if (StringUtils.hasText(state)) {
            indexes.put(getOAuth2AuthorizationIndexKey(OAuth2ParameterNames.STATE, state), null);
        }
        OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCodeToken =
                oAuth2Authorization.getToken(OAuth2AuthorizationCode.class);
        if (authorizationCodeToken != null && (includeInactive || authorizationCodeToken.isActive())) {
            OAuth2AuthorizationCode token = authorizationCodeToken.getToken();
            indexes.put(getOAuth2AuthorizationIndexKey(OAuth2ParameterNames.CODE,
                    token.getTokenValue()), getTokenTtlMs(token, now));
        }
        OAuth2Authorization.Token<OAuth2AccessToken> accessTokenToken =
                oAuth2Authorization.getToken(OAuth2AccessToken.class);
        if (accessTokenToken != null && (includeInactive || accessTokenToken.isActive())) {
            OAuth2AccessToken token = accessTokenToken.getToken();
            indexes.put(getOAuth2AuthorizationIndexKey(OAuth2TokenType.ACCESS_TOKEN.getValue(),
                    token.getTokenValue()), getTokenTtlMs(token, now));
        }
        OAuth2Authorization.Token<OidcIdToken> oidcIdTokenToken = oAuth2Authorization.getToken(OidcIdToken.class);
        if (oidcIdTokenToken != null && (includeInactive || oidcIdTokenToken.isActive())) {
            OidcIdToken token = oidcIdTokenToken.getToken();
            indexes.put(getOAuth2AuthorizationIndexKey(OidcParameterNames.ID_TOKEN,
                    token.getTokenValue()), getTokenTtlMs(token, now));
        }
        OAuth2Authorization.Token<OAuth2RefreshToken> refreshTokenToken =
                oAuth2Authorization.getToken(OAuth2RefreshToken.class);
        if (refreshTokenToken != null && (includeInactive || refreshTokenToken.isActive())) {
            OAuth2RefreshToken token = refreshTokenToken.getToken();
            indexes.put(getOAuth2AuthorizationIndexKey(OAuth2TokenType.REFRESH_TOKEN.getValue(),
                    token.getTokenValue()), getTokenTtlMs(token, now));
        }
        OAuth2Authorization.Token<OAuth2UserCode> userCodeToken = oAuth2Authorization.getToken(OAuth2UserCode.class);
        if (userCodeToken != null && (includeInactive || userCodeToken.isActive())) {
            OAuth2UserCode token = userCodeToken.getToken();
            indexes.put(getOAuth2AuthorizationIndexKey(OAuth2ParameterNames.USER_CODE,
                    token.getTokenValue()), getTokenTtlMs(token, now));
        }
        OAuth2Authorization.Token<OAuth2DeviceCode> deviceCodeToken =
                oAuth2Authorization.getToken(OAuth2DeviceCode.class);
        if (deviceCodeToken != null && (includeInactive || deviceCodeToken.isActive())) {
            OAuth2DeviceCode token = deviceCodeToken.getToken();
            indexes.put(getOAuth2AuthorizationIndexKey(OAuth2ParameterNames.DEVICE_CODE,
                    token.getTokenValue()), getTokenTtlMs(token, now));
        }
        return indexes;
    }

    private Long getTokenTtlMs(AbstractOAuth2Token token, Instant base) {
        Instant expiresAt = token.getExpiresAt();
        return expiresAt != null ? ChronoUnit.MILLIS.between(base, expiresAt) : null;
    }
}
