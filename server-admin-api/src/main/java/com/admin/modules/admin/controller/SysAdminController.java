package com.admin.modules.admin.controller;

import com.admin.modules.admin.constants.AdminConstants;
import com.admin.modules.admin.dto.AddAdminDto;
import com.admin.modules.admin.dto.EditAdminDto;
import com.admin.modules.admin.service.SysAdminService;
import com.admin.modules.admin.vo.SysAdminVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.api.CommonPage;
import com.common.api.CommonResult;
import com.common.api.ResultCode;
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
public class SysAdminController {

    @Autowired
    private SysAdminService sysAdminService;

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
        return CommonResult.success(sysAdminService.create(adminDto));
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
        return CommonResult.success(sysAdminService.editSysAdmin(id, editAdminDto));
    }

    /**
     * @Description: 删除
     * @param: id
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable(name = "id", required = true) Integer id) {
        return CommonResult.success(sysAdminService.deleteById(id));
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