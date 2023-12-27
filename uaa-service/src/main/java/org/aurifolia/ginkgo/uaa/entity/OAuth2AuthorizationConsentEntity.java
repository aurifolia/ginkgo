package org.aurifolia.ginkgo.uaa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

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
public class OAuth2AuthorizationConsentEntity {
    private String registeredClientId;
    private String principalName;
    private String authorities;

    /**
     * 转OAuth2AuthorizationConsent
     *
     * @return OAuth2AuthorizationConsent
     */
    public OAuth2AuthorizationConsent toOAuth2AuthorizationConsent() {
        OAuth2AuthorizationConsent.Builder builder = OAuth2AuthorizationConsent.withId(registeredClientId, principalName);
        if (StringUtils.hasText(authorities)) {
            Arrays.stream(StringUtils.commaDelimitedListToStringArray(authorities))
                    .map(SimpleGrantedAuthority::new).forEach(builder::authority);
        }
        return builder.build();
    }

    /**
     * 转OAuth2AuthorizationConsentEntity
     *
     * @param authorizationConsent OAuth2AuthorizationConsent
     * @return OAuth2AuthorizationConsentEntity
     */
    public static OAuth2AuthorizationConsentEntity from(OAuth2AuthorizationConsent authorizationConsent) {
        OAuth2AuthorizationConsentEntityBuilder builder = OAuth2AuthorizationConsentEntity.builder()
                .registeredClientId(authorizationConsent.getRegisteredClientId())
                .principalName(authorizationConsent.getPrincipalName());
        Set<GrantedAuthority> consentAuthorities = authorizationConsent.getAuthorities();
        if (!CollectionUtils.isEmpty(consentAuthorities)) {
            builder.authorities(StringUtils.collectionToCommaDelimitedString(consentAuthorities));
        }
        return builder.build();
    }
}
