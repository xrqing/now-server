package com.admin.modules.admin.service.impl;

import com.admin.modules.admin.mapper.SysAdminMapper;
import com.admin.modules.admin.service.SysAdminService;
import com.admin.modules.login.constants.LoginConstant;
import com.admin.security.domain.AdminDetails;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.pojo.SysAdmin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Description: 管理员 serviceImpl
 * @auther: xrq
 * @date: 2020/10/6 11:19
 */
@Slf4j
@Service
public class SysAdminServiceImpl implements SysAdminService {

    @Autowired
    private SysAdminMapper sysAdminMapper;

    /**
     * @Description: 获取管理员信息
     * @param: username 用户名
     */
    @Override
    public AdminDetails loadAdminByUsername(String username) {
        SysAdmin exit_sysAdmin = sysAdminMapper.selectOne(new QueryWrapper<SysAdmin>().eq("username", username));
        if (exit_sysAdmin != null) {
            return new AdminDetails(exit_sysAdmin);
        }
        throw new UsernameNotFoundException(LoginConstant.USERNAME_OR_PASSWORD_IS_ERROR);
    }
}
