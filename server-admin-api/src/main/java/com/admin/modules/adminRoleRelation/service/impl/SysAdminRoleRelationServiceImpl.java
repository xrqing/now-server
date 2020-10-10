package com.admin.modules.adminRoleRelation.service.impl;

import com.admin.modules.adminRoleRelation.mapper.SysAdminRoleRelationMapper;
import com.admin.modules.adminRoleRelation.service.SysAdminRoleRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 管理员与角色关系 serviceImpl
 * @auther: xrq
 * @date: 2020/10/10 15:41
 */
@Slf4j
@Service
public class SysAdminRoleRelationServiceImpl implements SysAdminRoleRelationService {

    @Autowired
    private SysAdminRoleRelationMapper sysAdminRoleRelationMapper;

}
