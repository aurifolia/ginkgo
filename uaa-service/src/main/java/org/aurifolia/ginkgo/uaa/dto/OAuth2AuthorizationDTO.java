package org.aurifolia.ginkgo.uaa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aurifolia.ginkgo.commons.constant.StringConstant;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * 用于构造OAuth2Authorization
 *
 * @author VNElinpe
 * @since 1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2AuthorizationDTO {
    private String id;
    private String registeredClientId;
    private String principalName;
    private String authorizationGrantType;
    private String authorizedScopes;
    private Map<String, Object> attributes;
    private String state;
    private String authorizationCodeValue;
    private Instant authorizationCodeIssuedAt;
    private Instant authorizationCodeExpiresAt;
    private Map<String, Object> authorizationCodeMetadata;
    private String accessTokenValue;
    private Instant accessTokenIssuedAt;
    private Instant accessTokenExpiresAt;
    private Map<String, Object> accessTokenMetadata;
    private String accessTokenType;
    private String accessTokenScopes;
    private String oidcIdTokenValue;
    private Instant oidcIdTokenIssuedAt;
    private Instant oidcIdTokenExpiresAt;
    private Map<String, Object> oidcIdTokenMetadata;
    private String refreshTokenValue;
    private Instant refreshTokenIssuedAt;
    private Instant refreshTokenExpiresAt;
    private Map<String, Object> refreshTokenMetadata;
    private String userCodeValue;
    private Instant userCodeIssuedAt;
    private Instant userCodeExpiresAt;
    private Map<String, Object> userCodeMetadata;
    private String deviceCodeValue;
    private Instant deviceCodeIssuedAt;
    private Instant deviceCodeExpiresAt;
    private Map<String, Object> deviceCodeMetadata;

    /**
     * 转OAuth2Authorization
     *
     * @return OAuth2Authorization
     */
    public OAuth2Authorization toOAuth2Authorization() {
        return toOAuth2Authorization(RegisteredClient.withId(registeredClientId).clientId(registeredClientId)
                .authorizationGrantType(new AuthorizationGrantType(StringConstant.NONE)).build());
    }

    /**
     * 转OAuth2Authorization
     *
     * @param registeredClient 客户端
     * @return OAuth2Authorization
     */
    public OAuth2Authorization toOAuth2Authorization(RegisteredClient registeredClient) {
        OAuth2Authorization.Builder builder = OAuth2Authorization.withRegisteredClient(registeredClient)
                .id(id)
                .principalName(principalName)
                .authorizationGrantType(new AuthorizationGrantType(authorizationGrantType))
                .attributes(attr -> attr.putAll(attributes));
        if (StringUtils.hasText(state)) {
            builder.attribute(OAuth2ParameterNames.STATE, state);
        }
        if (StringUtils.hasText(authorizedScopes)) {
            builder.authorizedScopes(StringUtils.commaDelimitedListToSet(authorizedScopes));
        }
        if (StringUtils.hasText(authorizationCodeValue)) {
            builder.token(new OAuth2AuthorizationCode(authorizationCodeValue, authorizationCodeIssuedAt,
                    authorizationCodeExpiresAt), metadata -> metadata.putAll(authorizationCodeMetadata));
        }
        if (StringUtils.hasText(accessTokenValue)) {
            OAuth2AccessToken.TokenType tokenType = null;
            if (OAuth2AccessToken.TokenType.BEARER.getValue().equals(accessTokenType)) {
                tokenType = OAuth2AccessToken.TokenType.BEARER;
            }
            Set<String> scopes = StringUtils.hasText(accessTokenScopes) ?
                    StringUtils.commaDelimitedListToSet(accessTokenScopes) : Collections.emptySet();
            builder.token(new OAuth2AccessToken(tokenType, accessTokenValue, accessTokenIssuedAt,
                    accessTokenExpiresAt, scopes), metadata -> metadata.putAll(accessTokenMetadata));
        }
        if (StringUtils.hasText(oidcIdTokenValue)) {
            builder.token(new OidcIdToken(oidcIdTokenValue, oidcIdTokenIssuedAt, oidcIdTokenExpiresAt,
                    (Map<String, Object>) oidcIdTokenMetadata.get(OAuth2Authorization.Token.CLAIMS_METADATA_NAME)),
                    metadata -> metadata.putAll(oidcIdTokenMetadata));
        }
        if (StringUtils.hasText(refreshTokenValue)) {
            builder.token(new OAuth2RefreshToken(refreshTokenValue, refreshTokenIssuedAt, refreshTokenExpiresAt),
                    metadata -> metadata.putAll(refreshTokenMetadata));
        }
        if (StringUtils.hasText(userCodeValue)) {
            builder.token(new OAuth2UserCode(userCodeValue, userCodeIssuedAt, userCodeExpiresAt),
                    metadata -> metadata.putAll(userCodeMetadata));
        }
        if (StringUtils.hasText(deviceCodeValue)) {
            builder.token(new OAuth2DeviceCode(deviceCodeValue, deviceCodeIssuedAt, deviceCodeExpiresAt),
                    metadata -> metadata.putAll(deviceCodeMetadata));
        }
        return builder.build();
    }

    /**
     * OAuth2Authorization转OAuth2AuthorizationDTO
     *
     * @param oAuth2Authorization OAuth2Authorization
     * @return OAuth2AuthorizationDTO
     */
    public static OAuth2AuthorizationDTO from(OAuth2Authorization oAuth2Authorization) {
        OAuth2AuthorizationDTOBuilder builder = OAuth2AuthorizationDTO.builder()
                .id(oAuth2Authorization.getId())
                .registeredClientId(oAuth2Authorization.getRegisteredClientId())
                .principalName(oAuth2Authorization.getPrincipalName())
                .authorizationGrantType(oAuth2Authorization.getAuthorizationGrantType().getValue())
                .attributes(oAuth2Authorization.getAttributes())
                .state((String) oAuth2Authorization.getAttributes().get(OAuth2ParameterNames.STATE));
        Set<String> oauth2AuthorizedScopes = oAuth2Authorization.getAuthorizedScopes();
        if (!CollectionUtils.isEmpty(oauth2AuthorizedScopes)) {
            builder.authorizedScopes(StringUtils.collectionToCommaDelimitedString(oauth2AuthorizedScopes));
        }
        OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCodeToken =
                oAuth2Authorization.getToken(OAuth2AuthorizationCode.class);
        if (authorizationCodeToken != null) {
            OAuth2AuthorizationCode token = authorizationCodeToken.getToken();
            builder.authorizationCodeValue(token.getTokenValue());
            builder.authorizationCodeIssuedAt(token.getIssuedAt());
            builder.authorizationCodeExpiresAt(token.getExpiresAt());
            builder.authorizationCodeMetadata(authorizationCodeToken.getMetadata());
        }
        OAuth2Authorization.Token<OAuth2AccessToken> accessTokenToken =
                oAuth2Authorization.getToken(OAuth2AccessToken.class);
        if (accessTokenToken != null) {
            OAuth2AccessToken token = accessTokenToken.getToken();
            builder.accessTokenValue(token.getTokenValue());
            builder.accessTokenType(token.getTokenType().getValue());
            builder.accessTokenIssuedAt(token.getIssuedAt());
            builder.accessTokenExpiresAt(token.getExpiresAt());
            builder.accessTokenMetadata(accessTokenToken.getMetadata());
        }
        OAuth2Authorization.Token<OidcIdToken> oidcIdTokenToken = oAuth2Authorization.getToken(OidcIdToken.class);
        if (oidcIdTokenToken != null) {
            OidcIdToken token = oidcIdTokenToken.getToken();
            builder.oidcIdTokenValue(token.getTokenValue());
            builder.oidcIdTokenIssuedAt(token.getIssuedAt());
            builder.oidcIdTokenExpiresAt(token.getExpiresAt());
            builder.oidcIdTokenMetadata(oidcIdTokenToken.getMetadata());
        }
        OAuth2Authorization.Token<OAuth2RefreshToken> refreshTokenToken =
                oAuth2Authorization.getToken(OAuth2RefreshToken.class);
        if (refreshTokenToken != null) {
            OAuth2RefreshToken token = refreshTokenToken.getToken();
            builder.refreshTokenValue(token.getTokenValue());
            builder.refreshTokenIssuedAt(token.getIssuedAt());
            builder.refreshTokenExpiresAt(token.getExpiresAt());
            builder.refreshTokenMetadata(refreshTokenToken.getMetadata());
        }
        OAuth2Authorization.Token<OAuth2UserCode> userCodeToken = oAuth2Authorization.getToken(OAuth2UserCode.class);
        if (userCodeToken != null) {
            OAuth2UserCode token = userCodeToken.getToken();
            builder.userCodeValue(token.getTokenValue());
            builder.userCodeIssuedAt(token.getIssuedAt());
            builder.userCodeExpiresAt(token.getExpiresAt());
            builder.userCodeMetadata(userCodeToken.getMetadata());
        }
        OAuth2Authorization.Token<OAuth2DeviceCode> deviceCodeToken =
                oAuth2Authorization.getToken(OAuth2DeviceCode.class);
        if (deviceCodeToken != null) {
            OAuth2DeviceCode token = deviceCodeToken.getToken();
            builder.deviceCodeValue(token.getTokenValue());
            builder.deviceCodeIssuedAt(token.getIssuedAt());
            builder.deviceCodeExpiresAt(token.getExpiresAt());
            builder.deviceCodeMetadata(deviceCodeToken.getMetadata());
        }
        return builder.build();
    }
}
