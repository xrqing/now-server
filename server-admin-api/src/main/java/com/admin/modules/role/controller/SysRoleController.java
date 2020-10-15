package com.admin.modules.role.controller;

import com.admin.base.BaseController;
import com.admin.modules.admin.constants.AdminConstants;
import com.admin.modules.adminRoleRelation.mapper.SysAdminRoleRelationMapper;
import com.admin.modules.role.constants.RoleConstant;
import com.admin.modules.role.mapper.SysRoleMapper;
import com.admin.modules.role.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.api.CommonPage;
import com.common.api.CommonResult;
import com.common.api.ResultCode;
import com.entity.pojo.SysAdminRoleRelation;
import com.entity.pojo.SysRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 角色管理 controller
 * @auther: xrq
 * @date: 2020/10/6 15:22
 */
@Slf4j
@RestController
@RequestMapping("/role")
@Api(tags = "SysRoleController", description = "角色管理")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysAdminRoleRelationMapper sysAdminRoleRelationMapper;

    /**
     * @Description: 分页获取角色列表
     * @param: pageSize 每页大小
     * @param: pageNum 页数
     * @param: name 角色名称
     */
    @ApiOperation("分页获取角色列表")
    @GetMapping("/list")
    public CommonResult list(@RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                             @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "name", required = false) String name) {
        Page<SysRole> list = sysRoleService.selectRoleList(pageSize, pageNum, name);
        if (list != null) {
            return CommonResult.success(ResultCode.SUCCESS.getCode(), RoleConstant.GET_ROLE_LIST_SUCCESS, CommonPage.restPage(list));
        }
        return CommonResult.failed(RoleConstant.GET_ROLE_LIST_FAIL);
    }

    /**
     * @Description: 新增
     * @param: SysRole
     */
    @ApiOperation("新增")
    @PostMapping("/create")
    public CommonResult create(@RequestBody SysRole sysRole) {
        if (sysRole.getName() == null) {
            return CommonResult.failed(RoleConstant.ROLE_NAME_IS_NOT_NULL);
        }
        if (sysRole.getDescription() == null) {
            return CommonResult.failed(RoleConstant.ROLE_DESCRIPTION_IS_NOT_NULL);
        }
        if (sysRoleMapper.selectOne(new QueryWrapper<SysRole>().eq("name", sysRole.getName())) != null) {
            return CommonResult.failed(RoleConstant.ROLE_NAME_IS_EXIST);
        }
        boolean b = sysRoleService.create(sysRole);
        if (b == true) {
            return CommonResult.success(ResultCode.SUCCESS.getCode(), RoleConstant.ROLE_NAME_ADD_SUCCESS, b);
        }
        return CommonResult.failed(RoleConstant.ROLE_NAME_ADD_FAIL);
    }

    /**
     * @Description: 编辑
     * @param: id
     * @param: SysRole
     */
    @ApiOperation("编辑")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable(name = "id", required = true) Integer id,
                               @RequestBody SysRole sysRole) {
        boolean b = sysRoleService.update(id, sysRole);
        if (b == true) {
            return CommonResult.success(ResultCode.SUCCESS.getCode(), RoleConstant.ROLE_NAME_UPDATE_SUCCESS, b);
        }
        return CommonResult.failed(RoleConstant.ROLE_NAME_UPDATE_FAIL);
    }

    /**
     * @Description: 获取详情
     * @param: id
     */
    @ApiOperation("获取详情")
    @GetMapping("/{id}")
    public CommonResult getItem(@PathVariable(name = "id", required = true) Integer id) {
        return CommonResult.success(sysRoleService.getItem(id));
    }

    /**
     * @Description: 删除
     * @param: id
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable(name = "id", required = true) Integer id) {
        SysAdminRoleRelation exit_sysAdminRoleRelation = sysAdminRoleRelationMapper.selectOne(new QueryWrapper<SysAdminRoleRelation>().eq("admin_id", getAdminId()));
        if (exit_sysAdminRoleRelation != null) {
            if (exit_sysAdminRoleRelation.getRoleId() == 1) {
                return CommonResult.success(ResultCode.SUCCESS.getCode(), RoleConstant.ROLE_NAME_DELETE_SUCCESS, sysRoleService.delete(id));
            }
            return CommonResult.failed(AdminConstants.CJ_ADMIN_NOT);
        }
        return CommonResult.failed(AdminConstants.QUAN_XIAN_NOT);
    }

    /**
     * @Description: 修改角色状态
     * @param: id
     * @param: status 状态（0-禁用， 1-启用）
     */
    @ApiOperation("修改角色状态")
    @PostMapping("/{id}/{status}")
    public CommonResult updateStatus(@PathVariable(name = "id", required = true) Integer id,
                                     @PathVariable(name = "status", required = true) Integer status) {
        return CommonResult.success(sysRoleService.updateStatus(id, status));
    }
}
