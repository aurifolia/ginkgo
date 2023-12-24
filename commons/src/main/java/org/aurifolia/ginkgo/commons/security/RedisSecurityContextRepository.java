package org.aurifolia.ginkgo.commons.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aurifolia.ginkgo.commons.util.RandomUtil;
import org.aurifolia.ginkgo.commons.entity.Principal;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.aurifolia.ginkgo.commons.constant.AuthorizationConstant.SECURITY_CONTEXT;
import static org.aurifolia.ginkgo.commons.constant.AuthorizationConstant.TOKEN_KEY;

/**
 * 使用Redis存取SecurityContext
 *
 * @author VNElinpe
 * @since 2023/10/5
 **/
@Slf4j
public class RedisSecurityContextRepository implements SecurityContextRepository {
    private final RedisTemplate<String, Object> redisTemplate;
    private long ttlMillis;

    public RedisSecurityContextRepository(@Qualifier("redisTemplate4Cache") RedisTemplate<String, Object> template) {
        this.redisTemplate = template;
    }

    @Override
    @Deprecated
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();
        SecurityContext securityContext = (SecurityContext) request.getAttribute(SECURITY_CONTEXT);
        if (securityContext != null) {
            return securityContext;
        }
        String cacheKey = getSecurityContextCacheKey(request);
        if (!StringUtils.hasText(cacheKey)) {
            return null;
        }
        Object principal = redisTemplate.opsForValue().get(cacheKey);
        if (principal == null) {
            return null;
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, null);
        securityContext = SecurityContextHolder.getContextHolderStrategy().createEmptyContext();
        securityContext.setAuthentication(authentication);
        request.setAttribute(SECURITY_CONTEXT, securityContext);
        return securityContext;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        String cacheKey = generateSecurityContextCacheKey();
        Principal principal = (Principal) context.getAuthentication().getPrincipal();
        principal.setPassword(null);
        redisTemplate.opsForValue().set(cacheKey, principal, ttlMillis, TimeUnit.MILLISECONDS);
        response.setHeader(TOKEN_KEY, cacheKey);
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        String securityContextCacheKey = getSecurityContextCacheKey(request);
        return StringUtils.hasText(securityContextCacheKey)
                && Boolean.TRUE.equals(redisTemplate.hasKey(securityContextCacheKey));
    }

    /**
     * 设置ttl
     *
     * @param duration 超时时间
     */
    public void setTtl(Duration duration) {
        ttlMillis = duration.toMillis();
    }

    /**
     * 获取SecurityContext的缓存Key
     *
     * @param request 当前请求
     * @return 缓存key
     */
    private String getSecurityContextCacheKey(HttpServletRequest request) {
        return request.getHeader(TOKEN_KEY);
    }

    /**
     * 生成缓存key
     *
     * @return 缓存key
     */
    private String generateSecurityContextCacheKey() {
        return RandomUtil.getUUID();
    }
}
