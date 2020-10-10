package com.admin.modules.admin.controller;

import com.admin.base.BaseController;
import com.admin.modules.admin.constants.AdminConstants;
import com.admin.modules.admin.dto.AddAdminDto;
import com.admin.modules.admin.dto.EditAdminDto;
import com.admin.modules.admin.mapper.SysAdminMapper;
import com.admin.modules.admin.service.SysAdminService;
import com.admin.modules.admin.vo.SysAdminVo;
import com.admin.modules.adminRoleRelation.mapper.SysAdminRoleRelationMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.api.CommonPage;
import com.common.api.CommonResult;
import com.common.api.ResultCode;
import com.entity.pojo.SysAdmin;
import com.entity.pojo.SysAdminRoleRelation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 管理员管理 controller
 * @auther: xrq
 * @date: 2020/10/6 15:20
 */
@Slf4j
@RestController
@RequestMapping("/admin")
@Api(tags = "SysAdminController", description = "管理员管理")
public class SysAdminController extends BaseController {

    @Autowired
    private SysAdminService sysAdminService;

    @Autowired
    private SysAdminMapper sysAdminMapper;

    @Autowired
    private SysAdminRoleRelationMapper sysAdminRoleRelationMapper;

    /**
     * @Description: 根据用户名分页查询管理员
     * @param: username 管理员名称
     * @param: pageSize 每页大小
     * @param: pageNum 多少页
     */
    @ApiOperation("根据用户名分页查询管理员")
    @GetMapping("/list")
    public CommonResult list(@RequestParam(name = "username", required = false) String username,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                             @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        Page<SysAdminVo> list = sysAdminService.getPageList(pageSize, pageNum, username);
        if (list != null) {
            return CommonResult.success(ResultCode.SUCCESS.getCode(), AdminConstants.ADMIN_LIST_SUCCESS, CommonPage.restPage(list));
        }
        return CommonResult.failed(AdminConstants.ADMIN_LIST_FAIL);
    }

    /**
     * @Description: 新增管理员
     * @param: AddAdminDto 新增参数
     */
    @ApiOperation("新增管理员")
    @PostMapping("/add")
    public CommonResult create(@RequestBody AddAdminDto adminDto) {
        //判断用户名和昵称是否相同
        if (sysAdminMapper.selectOne(new QueryWrapper<SysAdmin>().eq("username", adminDto.getUsername())) != null) {
            return CommonResult.failed(AdminConstants.ADD_SYS_ADMIN_IS_EXIST);
        }
        if (sysAdminMapper.selectOne(new QueryWrapper<SysAdmin>().eq("nick_name", adminDto.getNickName())) != null) {
            return CommonResult.failed(AdminConstants.NICKNAME_IS_EXIST);
        }
        return CommonResult.success(ResultCode.SUCCESS.getCode(), AdminConstants.ADD_ADMIN_SUCCESS, sysAdminService.create(adminDto));
    }

    /**
     * @Description: 修改管理员状态
     * @param: id
     * @param: status 状态（0-禁用， 1-启用）
     */
    @ApiOperation("修改管理员状态")
    @PostMapping("/{id}/{status}")
    public CommonResult updateStatus(@PathVariable(name = "id", required = true) Integer id,
                                     @PathVariable(name = "status", required = true) Integer status) {
        return CommonResult.success(sysAdminService.updateStatus(id, status));
    }

    /**
     * @Description: 根据id查询管理员信息
     * @param: id
     */
    @ApiOperation("根据id查询管理员信息")
    @GetMapping("/{id}")
    public CommonResult getDetail(@PathVariable(name = "id", required = true) Integer id) {
        return CommonResult.success(sysAdminService.getDetail(id));
    }

    /**
     * @Description: 编辑
     * @param: editAdminDto
     */
    @ApiOperation("编辑")
    @PostMapping("/edit/{id}")
    public CommonResult edit(@PathVariable(name = "id", required = true) Integer id,
                             @RequestBody EditAdminDto editAdminDto) {
        boolean b = sysAdminService.editSysAdmin(id, editAdminDto);
        if (b == true) {
            return CommonResult.success(ResultCode.SUCCESS.getCode(), AdminConstants.EDIT_ADMIN_SUCCESS, b);
        }
        return CommonResult.failed(AdminConstants.EDIT_ADMIN_FAIL);
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
                return CommonResult.success(ResultCode.SUCCESS.getCode(), AdminConstants.DELETE_ADMIN_SUCCESS, sysAdminService.deleteById(id));
            }
            return CommonResult.failed(AdminConstants.CJ_ADMIN_NOT);
        }
        return CommonResult.failed(AdminConstants.QUAN_XIAN_NOT);
    }

    /**
     * @Description: 分配角色
     * @param: adminId 管理员id
     * @param: roleId 角色id
     */
    @ApiOperation("分配角色")
    @PostMapping("/updateRole/{adminId}/{roleId}")
    public CommonResult updateRole(@PathVariable(name = "adminId", required = true) Integer adminId,
                                   @PathVariable(name = "roleId", required = true) Integer roleId) {
        return CommonResult.success(sysAdminService.updateRole(adminId, roleId));
    }
}