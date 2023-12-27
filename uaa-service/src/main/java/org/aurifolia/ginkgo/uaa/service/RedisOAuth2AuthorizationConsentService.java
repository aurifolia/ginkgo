package org.aurifolia.ginkgo.uaa.service;

import jakarta.annotation.Resource;
import org.aurifolia.ginkgo.uaa.entity.OAuth2AuthorizationConsentEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Set;

/**
 * TODO
 *
 * @author VNElinpe
 * @since 1.0
 **/
@Service
public class RedisOAuth2AuthorizationConsentService implements OAuth2AuthorizationConsentService {
    private static final String OAUTH2_CONSENT_KEY_PATTERN = "oauth2:consent:%s:%s";

    @Resource(name = "redisTemplate4Cache")
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        Set<GrantedAuthority> consentAuthorities = authorizationConsent.getAuthorities();
        if (!CollectionUtils.isEmpty(consentAuthorities)) {
            redisTemplate.opsForValue().set(getConsentKey(authorizationConsent.getRegisteredClientId(),
                    authorizationConsent.getPrincipalName()),
                    StringUtils.collectionToCommaDelimitedString(consentAuthorities));
        }
    }

    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        redisTemplate.delete(getConsentKey(authorizationConsent.getRegisteredClientId(),
                authorizationConsent.getPrincipalName()));
    }

    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        String authorities = (String) redisTemplate.opsForValue().get(getConsentKey(registeredClientId, principalName));
        if (!StringUtils.hasText(authorities)) {
            return null;
        }
        OAuth2AuthorizationConsent.Builder builder = OAuth2AuthorizationConsent
                .withId(registeredClientId, principalName);
        Arrays.stream(StringUtils.commaDelimitedListToStringArray(authorities))
                .map(SimpleGrantedAuthority::new).forEach(builder::authority);
        return builder.build();
    }

    private String getConsentKey(String registeredClientId, String principalName) {
        return String.format(OAUTH2_CONSENT_KEY_PATTERN, registeredClientId, principalName);
    }
}
