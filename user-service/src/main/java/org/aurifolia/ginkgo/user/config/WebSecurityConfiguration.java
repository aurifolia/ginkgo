package org.aurifolia.ginkgo.user.config;

import org.aurifolia.ginkgo.commons.security.RedisSecurityContextRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * TODO
 *
 * @author VNElinpe
 * @since 2023/10/2
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    @Bean
    public SecurityFilterChain web2(HttpSecurity security,
                                    RedisSecurityContextRepository securityContextRepository,
                                    UserDetailsService userDetailsService) throws Exception {
        security.authorizeHttpRequests(authorize ->
                authorize.requestMatchers("/role/test003").permitAll()
                        .anyRequest().permitAll());
        security.csrf(AbstractHttpConfigurer::disable);
        security.requestCache(RequestCacheConfigurer::disable);
        security.securityContext(securityContext ->
                securityContext.securityContextRepository(securityContextRepository));
        security.addFilterAfter(new RequestBodyAuthenticationFilter(authenticationManager(userDetailsService),
                securityContextRepository), LogoutFilter.class);
        return security.build();
    }

//    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(daoAuthenticationProvider);
    }

}
