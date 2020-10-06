package com.admin.security.domain;

import com.entity.pojo.SysAdmin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Description: SpringSecurity需要的用户详情
 * @auther: xrq
 * @date: 2020/9/11 21:32
 */
public class AdminDetails implements UserDetails {
    private SysAdmin sysAdmin;

    public AdminDetails(SysAdmin sysAdmin) {
        this.sysAdmin = sysAdmin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return sysAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return sysAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return sysAdmin.getStatus().equals(1);
    }

    public SysAdmin sysAdmin() {
        return sysAdmin;
    }
}
