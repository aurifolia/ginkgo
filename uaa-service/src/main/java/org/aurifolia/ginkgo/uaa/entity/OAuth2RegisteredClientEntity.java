package org.aurifolia.ginkgo.uaa.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aurifolia.ginkgo.commons.constant.StringConstant;
import org.aurifolia.ginkgo.commons.constant.TypeReferenceConstant;
import org.aurifolia.ginkgo.commons.core.ObjectMapperHolder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.ConfigurationSettingNames;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.aurifolia.ginkgo.commons.constant.TypeReferenceConstant.MAP_STRING_OBJECT;

/**
 * TODO
 *
 * @author VNElinpe
 * @since 1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2RegisteredClientEntity {
    private Long id;
    private String clientId;
    private Instant clientIdIssuedAt;
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;
    private String clientAuthenticationMethods;
    private String authorizationGrantTypes;
    private String redirectUris;
    private String postLogoutRedirectUris;
    private String scopes;
    private String clientSettings;
    private String tokenSettings;

    /**
     * 转RegisteredClient
     *
     * @return RegisteredClient
     */
    public RegisteredClient toRegisteredClient() {
        RegisteredClient.Builder builder = RegisteredClient.withId(id.toString())
                .clientId(clientId)
                .clientIdIssuedAt(clientIdIssuedAt)
                .clientSecretExpiresAt(clientSecretExpiresAt)
                .clientSecret(clientSecret)
                .clientName(clientName);
        StringUtils.commaDelimitedListToSet(clientAuthenticationMethods).stream().map(item -> {
            if (ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue().equals(item)) {
                return ClientAuthenticationMethod.CLIENT_SECRET_BASIC;
            } else if (ClientAuthenticationMethod.CLIENT_SECRET_POST.getValue().equals(item)) {
                return ClientAuthenticationMethod.CLIENT_SECRET_POST;
            } else if (ClientAuthenticationMethod.CLIENT_SECRET_JWT.getValue().equals(item)) {
                return ClientAuthenticationMethod.CLIENT_SECRET_JWT;
            } else if (ClientAuthenticationMethod.PRIVATE_KEY_JWT.getValue().equals(item)) {
                return ClientAuthenticationMethod.PRIVATE_KEY_JWT;
            } else if (ClientAuthenticationMethod.NONE.getValue().equals(item)) {
                return ClientAuthenticationMethod.NONE;
            } else {
                return new ClientAuthenticationMethod(item);
            }
        }).forEach(builder::clientAuthenticationMethod);
        StringUtils.commaDelimitedListToSet(authorizationGrantTypes).stream().map(item -> {
            if (AuthorizationGrantType.AUTHORIZATION_CODE.getValue().equals(item)) {
                return AuthorizationGrantType.AUTHORIZATION_CODE;
            } else if (AuthorizationGrantType.REFRESH_TOKEN.getValue().equals(item)) {
                return AuthorizationGrantType.REFRESH_TOKEN;
            } else if (AuthorizationGrantType.CLIENT_CREDENTIALS.getValue().equals(item)) {
                return AuthorizationGrantType.CLIENT_CREDENTIALS;
            } else if (AuthorizationGrantType.JWT_BEARER.getValue().equals(item)) {
                return AuthorizationGrantType.JWT_BEARER;
            } else if (AuthorizationGrantType.DEVICE_CODE.getValue().equals(item)) {
                return AuthorizationGrantType.DEVICE_CODE;
            } else {
                return new AuthorizationGrantType(item);
            }
        }).forEach(builder::authorizationGrantType);
        StringUtils.commaDelimitedListToSet(redirectUris).forEach(builder::redirectUri);
        StringUtils.commaDelimitedListToSet(postLogoutRedirectUris).forEach(builder::postLogoutRedirectUri);
        StringUtils.commaDelimitedListToSet(scopes).forEach(builder::scope);

        Map<String, Object> clientSettingsMap = ObjectMapperHolder.readValue(clientSettings, MAP_STRING_OBJECT);
        builder.clientSettings(ClientSettings.withSettings(clientSettingsMap).build());
        Map<String, Object> tokenSettingsMap = ObjectMapperHolder.readValue(tokenSettings, MAP_STRING_OBJECT);
        TokenSettings.Builder tokenSettingsBuilder = TokenSettings.withSettings(tokenSettingsMap);
        if (!tokenSettingsMap.containsKey(ConfigurationSettingNames.Token.ACCESS_TOKEN_FORMAT)) {
            tokenSettingsBuilder.accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED);
        }
        builder.tokenSettings(tokenSettingsBuilder.build());
        return builder.build();
    }

    /**
     * RegisteredClient转RegisteredClientEntity
     *
     * @param registeredClient RegisteredClient
     * @return RegisteredClientEntity
     */
    public static OAuth2RegisteredClientEntity from(RegisteredClient registeredClient) {
        OAuth2RegisteredClientEntityBuilder builder = OAuth2RegisteredClientEntity.builder().id(Long.parseLong(registeredClient.getId()))
                .clientId(registeredClient.getClientId())
                .clientIdIssuedAt(registeredClient.getClientIdIssuedAt())
                .clientSecret(registeredClient.getClientSecret())
                .clientSecretExpiresAt(registeredClient.getClientSecretExpiresAt())
                .clientName(registeredClient.getClientName())
                .clientAuthenticationMethods(registeredClient.getClientAuthenticationMethods().stream().map(ClientAuthenticationMethod::getValue).collect(Collectors.joining(StringConstant.COMMA)))
                .authorizationGrantTypes(registeredClient.getAuthorizationGrantTypes().stream().map(AuthorizationGrantType::getValue).collect(Collectors.joining(StringConstant.COMMA)))
                .scopes(StringUtils.collectionToCommaDelimitedString(registeredClient.getScopes()))
                .clientSettings(ObjectMapperHolder.writeValue(registeredClient.getClientSettings().getSettings()))
                .tokenSettings(ObjectMapperHolder.writeValue(registeredClient.getTokenSettings().getSettings()));
        Set<String> clientRedirectUris = registeredClient.getRedirectUris();
        if (!CollectionUtils.isEmpty(clientRedirectUris)) {
            builder.redirectUris(StringUtils.collectionToCommaDelimitedString(clientRedirectUris));
        }
        Set<String> clientPostLogoutRedirectUris = registeredClient.getPostLogoutRedirectUris();
        if (!CollectionUtils.isEmpty(clientRedirectUris)) {
            builder.postLogoutRedirectUris(StringUtils.collectionToCommaDelimitedString(clientPostLogoutRedirectUris));
        }
        return builder.build();
    }
}
