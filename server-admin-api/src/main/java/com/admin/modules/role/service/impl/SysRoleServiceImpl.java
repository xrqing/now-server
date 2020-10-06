package com.admin.modules.role.service.impl;

import com.admin.modules.role.mapper.SysRoleMapper;
import com.admin.modules.role.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 角色管理 serviceImpl
 * @auther: xrq
 * @date: 2020/10/6 15:22
 */
@Slf4j
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
}
