package org.aurifolia.ginkgo.user.config;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aurifolia.ginkgo.commons.core.ObjectMapperHolder;
import org.aurifolia.ginkgo.commons.constant.ResultCode;
import org.aurifolia.ginkgo.commons.dto.ResultDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.Map;

import static org.aurifolia.ginkgo.commons.constant.AuthorizationConstant.PASSWORD_KEY;
import static org.aurifolia.ginkgo.commons.constant.AuthorizationConstant.USERNAME_KEY;

/**
 * 处理application/json的登录请求
 *
 * @author VNElinpe
 * @since 2023/10/5
 **/
public class RequestBodyAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final TypeReference<Map<String, String>> DEFAULT_MAPPER_TYPE = new TypeReference<>() {};
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER =
            new AntPathRequestMatcher("/user/login", "POST");

    public RequestBodyAuthenticationFilter(AuthenticationManager authenticationManager,
                                           SecurityContextRepository securityContextRepository) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
        setSecurityContextRepository(securityContextRepository);
        setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            ObjectMapperHolder.getObjectMapper().writeValue(response.getOutputStream(), ResultDTO.success());
        });
        setAuthenticationFailureHandler((request, response, exception) -> {
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            ObjectMapperHolder.getObjectMapper().writeValue(response.getOutputStream(),
                    ResultDTO.from(ResultCode.INCORRECT_USER_ACCOUNT_OR_PASSWORD, null));
        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {
        Map<String, String> requestBody = ObjectMapperHolder.getObjectMapper()
                .readValue(request.getInputStream(), DEFAULT_MAPPER_TYPE);
        String username = requestBody.get(USERNAME_KEY);
        String password = requestBody.get(PASSWORD_KEY);
        UsernamePasswordAuthenticationToken authRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
