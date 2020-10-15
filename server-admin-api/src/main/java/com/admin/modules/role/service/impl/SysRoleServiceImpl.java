package com.admin.modules.role.service.impl;

import com.admin.base.BaseServiceImpl;
import com.admin.modules.admin.constants.AdminConstants;
import com.admin.modules.role.constants.RoleConstant;
import com.admin.modules.role.mapper.SysRoleMapper;
import com.admin.modules.role.service.SysRoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.exception.Asserts;
import com.entity.pojo.SysAdmin;
import com.entity.pojo.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description: 角色管理 serviceImpl
 * @auther: xrq
 * @date: 2020/10/6 15:22
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends BaseServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * @Description: 分页获取角色列表
     * @param: pageSize 每页大小
     * @param: pageNum 页数
     * @param: name 角色名称
     */
    @Override
    public Page<SysRole> selectRoleList(Integer pageSize, Integer pageNum, String name) {
        return sysRoleMapper.selectRoleList(new Page<>(pageNum, pageSize), name);
    }

    /**
     * @Description: 新增
     * @param: SysRole
     */
    @Override
    public boolean create(SysRole sysRole) {
        sysRole.setCreateTime(new Date());
        int count = sysRoleMapper.insert(sysRole);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * @Description: 新增
     * @param: id
     * @param: SysRole 实体
     */
    @Override
    public boolean update(Integer id, SysRole sysRole) {
        sysRole.setId(id);
        int count = sysRoleMapper.updateById(sysRole);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * @Description: 获取详情
     * @param: id
     */
    @Override
    public SysRole getItem(Integer id) {
        return sysRoleMapper.selectById(id);
    }

    /**
     * @Description: 删除
     * @param: id
     */
    @Override
    public boolean delete(Integer id) {
        int count = sysRoleMapper.deleteById(id);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * @Description: 修改角色状态
     * @param: id
     * @param: status 状态（0-禁用， 1-启用）
     */
    @Override
    public boolean updateStatus(Integer id, Integer status) {
        if (status == 0 || status == 1) {
            SysRole sysRole = new SysRole();
            sysRole.setId(id);
            sysRole.setStatus(status);
            int count = sysRoleMapper.updateById(sysRole);
            if (count <= 0) {
                Asserts.fail(RoleConstant.CHANGE_STATUS_FAIL);
            }
            return true;
        }
        return false;
    }
}
