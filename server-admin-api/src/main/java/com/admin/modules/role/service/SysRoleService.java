package com.admin.modules.role.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.entity.pojo.SysRole;

/**
 * @Description: 角色管理 service
 * @auther: xrq
 * @date: 2020/10/6 15:22
 */
public interface SysRoleService {

    /**
     * @Description: 分页获取角色列表
     * @param: pageSize 每页大小
     * @param: pageNum 页数
     * @param: name 角色名称
     */
    Page<SysRole> selectRoleList(Integer pageSize, Integer pageNum, String name);

    /**
     * @Description: 新增
     * @param: SysRole
     */
    boolean create(SysRole sysRole);

    /**
     * @Description: 编辑
     * @param: id
     * @param: SysRole 实体
     */
    boolean update(Integer id, SysRole sysRole);

    /**
     * @Description: 获取详情
     * @param: id
     */
    SysRole getItem(Integer id);

    /**
     * @Description: 删除
     * @param: id
     */
    boolean delete(Integer id);

    /**
     * @Description: 修改角色状态
     * @param: id
     * @param: status 状态（0-禁用， 1-启用）
     */
    boolean updateStatus(Integer id, Integer status);
}
