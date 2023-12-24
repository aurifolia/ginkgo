package org.aurifolia.ginkgo.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * 用于认证授权的用户基本信息
 *
 * @author VNElinpe
 * @since 1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Principal implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private Set<DefaultGrantedAuthority> authorities;
    private Boolean locked;
    private Boolean deleted;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return Boolean.FALSE.equals(locked);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.FALSE.equals(deleted);
    }
}
