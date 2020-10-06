package com.admin.modules.admin.service;

import com.admin.security.domain.AdminDetails;

/**
 * @Description: 管理员 service
 * @auther: xrq
 * @date: 2020/10/6 11:19
 */
public interface SysAdminService {

    /**
     * @Description: 获取管理员信息
     * @param: username 用户名
     */
    AdminDetails loadAdminByUsername(String username);
}
