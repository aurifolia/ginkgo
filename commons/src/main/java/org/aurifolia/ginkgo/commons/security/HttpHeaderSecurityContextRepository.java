package org.aurifolia.ginkgo.commons.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aurifolia.ginkgo.commons.core.ObjectMapperHolder;
import org.aurifolia.ginkgo.commons.entity.Principal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.aurifolia.ginkgo.commons.constant.AuthorizationConstant.*;

/**
 * 从请求头里获取权限信息
 *
 * @author VNElinpe
 * @since 1.0
 **/
@Slf4j
public class HttpHeaderSecurityContextRepository implements SecurityContextRepository {
    @Override
    @Deprecated
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();
        SecurityContext securityContext = (SecurityContext) request.getAttribute(SECURITY_CONTEXT);
        if (securityContext != null) {
            return securityContext;
        }
        String principalBase64 = request.getHeader(PRINCIPAL);
        if (!StringUtils.hasText(principalBase64)) {
            return null;
        }
        String principalJson = new String(Base64.getDecoder().decode(principalBase64), StandardCharsets.UTF_8);
        try {
            Principal principal = ObjectMapperHolder.getObjectMapper().readValue(principalJson, Principal.class);
            securityContext = SecurityContextHolder.getContextHolderStrategy().createEmptyContext();
            securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(principal, null));
            request.setAttribute(SECURITY_CONTEXT, securityContext);
            return securityContext;
        } catch (JsonProcessingException e) {
            // ignore
            log.error("deserialize principal fail.", e);
        }
        return null;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        System.out.println();
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return StringUtils.hasText(request.getHeader(PRINCIPAL)) || request.getAttribute(SECURITY_CONTEXT) != null;
    }
}
