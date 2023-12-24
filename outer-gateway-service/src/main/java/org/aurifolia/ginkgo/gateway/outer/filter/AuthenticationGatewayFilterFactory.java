package org.aurifolia.ginkgo.gateway.outer.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import org.aurifolia.ginkgo.commons.core.ObjectMapperHolder;
import org.aurifolia.ginkgo.commons.dto.ResultDTO;
import org.aurifolia.ginkgo.commons.entity.Principal;
import org.aurifolia.ginkgo.gateway.outer.core.UrlAcl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

import static org.aurifolia.ginkgo.commons.constant.ResultCode.UNAUTHORIZED_ACCESS;
import static org.aurifolia.ginkgo.commons.constant.ResultCode.USER_ACCESS_IS_BLOCKED;
import static org.aurifolia.ginkgo.commons.constant.Separator.COMMA;
import static org.aurifolia.ginkgo.commons.constant.AuthorizationConstant.AUTHORITIES_KEY;
import static org.aurifolia.ginkgo.commons.constant.AuthorizationConstant.TOKEN_KEY;
import static org.aurifolia.ginkgo.commons.constant.AuthorizationConstant.UID_KEY;
import static org.aurifolia.ginkgo.commons.constant.AuthorizationConstant.USERNAME_KEY;

/**
 * 用户认证相关的filter
 *
 * @author VNElinpe
 * @since 1.0
 **/
@Component
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
    @Resource(name = "reactiveRedisTemplate4Cache")
    private ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;
    @Autowired
    private UrlAcl urlAcl;
    @Autowired
    private MessageSource messageSource;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            String uri = request.getPath().toString();
            if (urlAcl.getBlockSet().contains(uri)) {
                return unAuthorize(request, response, USER_ACCESS_IS_BLOCKED);
            }
            String tokenKey = request.getHeaders().getFirst(TOKEN_KEY);
            if (!StringUtils.hasText(tokenKey)) {
                if (urlAcl.getAllowSet().contains(uri)) {
                    return chain.filter(exchange);
                } else {
                    return unAuthorize(request, response, UNAUTHORIZED_ACCESS);
                }
            }
            return reactiveRedisTemplate.opsForValue().get(tokenKey)
                    .map(item -> (Principal) item)
                    .flatMap(principal -> {
                        // 未授权或登录过期
                        if (principal == null) {
                            return null;
                        }
                        // 填充用户登录信息
                        ServerHttpRequest newRequest = request.mutate().header(UID_KEY, principal.getId().toString())
                                .header(USERNAME_KEY, principal.getUsername())
                                .header(AUTHORITIES_KEY, principal.getAuthorities().stream()
                                        .map(GrantedAuthority::getAuthority).collect(Collectors.joining(COMMA)))
                                .build();
                        return chain.filter(exchange.mutate().request(newRequest).build());
                    });
        };
    }

    private Mono<Void> unAuthorize(ServerHttpRequest request, ServerHttpResponse response, String code) {
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        try {
            return response.writeWith(Mono.just(response.bufferFactory()
                    .wrap(ObjectMapperHolder.getObjectMapper().writeValueAsBytes(ResultDTO.from(code,
                            messageSource.getMessage(code, null, request.getHeaders().getContentLanguage()), null)))));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
