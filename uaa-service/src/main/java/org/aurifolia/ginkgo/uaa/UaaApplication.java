package org.aurifolia.ginkgo.uaa;

import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.servlet.http.HttpServletRequest;
import org.aurifolia.ginkgo.commons.cache.RedisTemplate4CacheConfiguration;
import org.aurifolia.ginkgo.commons.util.RandomUtil;
import org.aurifolia.ginkgo.uaa.core.IdContainedOAuth2AuthorizationCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationProvider;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationConsentAuthenticationProvider;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.time.Duration;
import java.util.Set;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * 统一认证授权服务
 *
 * @author VNElinpe
 * @since 1.0
 **/
@EnableMethodSecurity
@SpringBootApplication
@Import(RedisTemplate4CacheConfiguration.class)
public class UaaApplication {
    public static void main(String[] args) {
        SpringApplication.run(UaaApplication.class, args);
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
        UserDetails admin123 = User.builder().username("admin123")
                .password("{noop}123qwe")
                .build();
        return new InMemoryUserDetailsManager(admin123);
    }

    @Bean
    public RegisteredClientRepository getRegisteredClientRepository() {
        RegisteredClient registeredClient = RegisteredClient.withId("test-id")
                .clientId("test-client-id")
                .clientName("test-client-name")
                .clientSecret("{noop}test-client-password")
                .redirectUri("https://www.baidu.com")
                .scope("read")
                .scope("write")
                .scope("cascade")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .authorizationGrantType(AuthorizationGrantType.DEVICE_CODE)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofHours(1)).refreshTokenTimeToLive(Duration.ofSeconds(20))
                        .authorizationCodeTimeToLive(Duration.ofMinutes(30)).idTokenSignatureAlgorithm(SignatureAlgorithm.ES256)
                        .reuseRefreshTokens(false).build()).build();
        return new InMemoryRegisteredClientRepository(registeredClient);
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> getOAuth2TokenCustomizer() {
        return context -> context.getJwsHeader().algorithm(SignatureAlgorithm.ES256);
    }

    @Bean
    JWKSource<SecurityContext> jwkSource() {
        ECKey ecKey = getEcKey();
        JWKSet jwkSet = new JWKSet(ecKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    private static ECKey getEcKey() {
        KeyPair keyPair = generateEcKey();
        return new ECKey.Builder(Curve.P_256, (ECPublicKey) keyPair.getPublic())
                .privateKey((ECPrivateKey) keyPair.getPrivate())
                .keyID("3fc97cee1d034584813d0ec03d348aa2").build();
    }

    private static KeyPair generateEcKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            keyPairGenerator.initialize(Curve.P_256.toECParameterSpec());
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    private static KeyPair generateEd25519Key() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("Ed25519");
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

//    @Bean
    public OAuth2TokenGenerator getTokenGenerator() {
        return new DelegatingOAuth2TokenGenerator(context -> null);
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain applyDefaultSecurity(HttpSecurity http) throws Exception {
//        authorizationServerConfigurer.authorizationEndpoint(oAuth2AuthorizationEndpointConfigurer -> {
//            oAuth2AuthorizationEndpointConfigurer.authenticationProviders(item -> {
//                item.forEach(elem -> {
//                    if (elem instanceof OAuth2AuthorizationConsentAuthenticationProvider provider) {
//                        provider.setAuthorizationCodeGenerator(new IdContainedOAuth2AuthorizationCodeGenerator());
//                    }
//                    else if (elem instanceof OAuth2AuthorizationCodeRequestAuthenticationProvider provider) {
//                        provider.setAuthorizationCodeGenerator(new IdContainedOAuth2AuthorizationCodeGenerator());
//                    }
//                });
//                System.out.println();
//            });
//        });
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(withDefaults());
        http.oauth2ResourceServer((resourceServer) -> resourceServer.jwt(withDefaults()));
        http.exceptionHandling((exceptions) -> exceptions.defaultAuthenticationEntryPointFor(
                new LoginUrlAuthenticationEntryPoint("/login"), createRequestMatcher()));
        return http.build();
    }

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain getSecurityFilterChain1(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated()).formLogin(withDefaults());
        http.addFilterBefore(new BearerTokenAuthenticationFilter(authenticationManager), RequestCacheAwareFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, JwtDecoder jwtDecoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider(jwtDecoder);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        jwtAuthenticationProvider.setJwtAuthenticationConverter(jwtAuthenticationConverter);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(daoAuthenticationProvider, jwtAuthenticationProvider);
    }

    private static RequestMatcher createRequestMatcher() {
        MediaTypeRequestMatcher requestMatcher = new MediaTypeRequestMatcher(MediaType.TEXT_HTML);
        requestMatcher.setIgnoredMediaTypes(Set.of(MediaType.ALL));
        return requestMatcher;
    }
}
