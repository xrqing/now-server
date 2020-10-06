package com.admin.config;

import com.admin.modules.admin.service.SysAdminService;
import com.admin.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Description: 安全配置
 * @auther: xrq
 * @date: 2020/10/6 12:53
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AdminSecurityConfig extends SecurityConfig {

    @Autowired
    private SysAdminService sysAdminService;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> sysAdminService.loadAdminByUsername(username);
    }
}
