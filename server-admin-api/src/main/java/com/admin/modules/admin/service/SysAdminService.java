package com.admin.modules.admin.service;

import com.admin.modules.admin.dto.AddAdminDto;
import com.admin.modules.admin.dto.EditAdminDto;
import com.admin.modules.admin.vo.SysAdminDetailVo;
import com.admin.modules.admin.vo.SysAdminVo;
import com.admin.security.domain.AdminDetails;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.entity.pojo.SysAdmin;

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

    /**
     * @Description: 根据用户名分页查询管理员
     * @param: username 管理员名称
     * @param: pageSize 每页大小
     * @param: pageNum 多少页
     */
    Page<SysAdminVo> getPageList(Integer pageSize, Integer pageNum, String username);

    /**
     * @Description: 新增管理员
     * @param: AddAdminDto 新增参数
     */
    SysAdmin create(AddAdminDto adminDto);

    /**
     * @Description: 修改管理员状态
     * @param: id
     * @param: status 状态（0-禁用， 1-启用）
     */
    boolean updateStatus(Integer id, Integer status);

    /**
     * @Description: 根据id查询管理员信息
     * @param: id
     */
    SysAdminDetailVo getDetail(Integer id);

    /**
     * @Description: 编辑
     * @param: editAdminDto
     */
    boolean editSysAdmin(Integer id, EditAdminDto editAdminDto);

    /**
     * @Description: 删除
     * @param: id
     */
    boolean deleteById(Integer id);

    /**
     * @Description: 分配角色
     * @param: adminId 管理员id
     * @param: roleId 角色id
     */
    boolean updateRole(Integer adminId, Integer roleId);
}
