package com.admin.modules.login.service;

import java.util.HashMap;

/**
 * @Description: 管理员登录 service
 * @auther: xrq
 * @date: 2020/10/6 11:14
 */
public interface LoginService {

    /**
     * @Description: 管理员登陆
     * @param: username 用户名
     * @param: password 密码
     */
    HashMap login(String username, String password);
}
