package org.aurifolia.ginkgo.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * 授予用户的单个权限
 *
 * @author VNElinpe
 * @since 1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefaultGrantedAuthority implements GrantedAuthority {
    private String authority;
    @Override
    public String getAuthority() {
        return null;
    }
}
