package com.admin.modules.role.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.entity.pojo.SysRole;
import org.springframework.data.repository.query.Param;

/**
 * @Description: 角色管理 mapper
 * @auther: xrq
 * @date: 2020/10/6 15:22
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * @Description: 分页获取角色列表
     * @param: pageSize 每页大小
     * @param: pageNum 页数
     * @param: name 角色名称
     */
    Page<SysRole> selectRoleList(@Param("page") Page<SysRole> page, @Param("name") String name);
}
