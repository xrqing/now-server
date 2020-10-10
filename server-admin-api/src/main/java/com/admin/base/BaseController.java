package com.admin.base;

import com.admin.security.domain.AdminDetails;
import com.entity.pojo.SysAdmin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Description: 基础控制器
 * @auther: xrq
 * @date: 2020/9/13 21:36
 */
@Slf4j
public class BaseController {

    /**
     * @Description: 获取当前登录的管理员信息
     * @param:
     */
    public SysAdmin getCurrentSysAdmin() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        AdminDetails adminDetails = (AdminDetails) authentication.getPrincipal();
        return adminDetails.sysAdmin();
    }

    /**
     * @Description: 获取当前登录的管理员username
     * @param:
     */
    public String getUsername() {
        return getCurrentSysAdmin().getUsername();
    }

    /**
     * @Description: 获取当前登录的管理员id
     * @param:
     */
    public Integer getAdminId() {
        return getCurrentSysAdmin().getId();
    }
}
