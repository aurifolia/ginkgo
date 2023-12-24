package org.aurifolia.ginkgo.uaa.core;

import org.aurifolia.ginkgo.commons.util.RandomUtil;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.util.StringUtils;

import java.time.Instant;

/**
 * 包含OAuth2Authorization.getId()的OAuth2AuthorizationCode生成器
 *
 * @author VNElinpe
 * @since 1.0
 **/
public class IdContainedOAuth2AuthorizationCodeGenerator implements OAuth2TokenGenerator<OAuth2AuthorizationCode> {
    @Override
    public OAuth2AuthorizationCode generate(OAuth2TokenContext context) {
        OAuth2TokenType tokenType = context.getTokenType();
        if (tokenType == null || !OAuth2ParameterNames.CODE.equals(tokenType.getValue())) {
            return null;
        }
        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plus(context.getRegisteredClient().getTokenSettings().getAuthorizationCodeTimeToLive());
        String tokenValue = RandomUtil.getUUID() + context.getAuthorization().getId();
        return new OAuth2AuthorizationCode(tokenValue, issuedAt, expiresAt);
    }
}
