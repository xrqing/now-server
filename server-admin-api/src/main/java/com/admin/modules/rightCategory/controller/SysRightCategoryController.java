package com.admin.modules.rightCategory.controller;

import com.admin.base.BaseController;
import com.admin.modules.admin.constants.AdminConstants;
import com.admin.modules.adminRoleRelation.mapper.SysAdminRoleRelationMapper;
import com.admin.modules.rightCategory.constants.RightCategoryConstant;
import com.admin.modules.rightCategory.mapper.SysRightCategoryMapper;
import com.admin.modules.rightCategory.service.SysRightCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.api.CommonPage;
import com.common.api.CommonResult;
import com.common.api.ResultCode;
import com.entity.pojo.SysAdminRoleRelation;
import com.entity.pojo.SysRightCategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 权限资源分类 controller
 * @auther: xrq
 * @date: 2020/10/14 12:02
 */
@Slf4j
@RestController
@RequestMapping("/rightCategory")
@Api(tags = "SysRightCategoryController", description = "权限资源分类管理")
public class SysRightCategoryController extends BaseController {

    @Autowired
    private SysRightCategoryService sysRightCategoryService;

    @Autowired
    private SysRightCategoryMapper sysRightCategoryMapper;

    @Autowired
    private SysAdminRoleRelationMapper sysAdminRoleRelationMapper;

    /**
     * @Description: 分页获取权限资源分类列表
     * @param: pageSize 每页大小
     * @param: pageNum 页数
     * @param: name 分类名称
     */
    @ApiOperation("分页获取权限资源分类列表")
    @GetMapping("/list")
    public CommonResult list(@RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                             @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "name", required = false) String name) {
        Page<SysRightCategory> list = sysRightCategoryService.queryList(pageSize, pageNum, name);
        if (list != null) {
            return CommonResult.success(ResultCode.SUCCESS.getCode(), RightCategoryConstant.GET_RIGHT_CATEGORY_LIST_SUCCESS, CommonPage.restPage(list));
        }
        return CommonResult.failed(RightCategoryConstant.GET_RIGHT_CATEGORY_LIST_FAIL);
    }

    /**
     * @Description: 新增
     * @param: SysRightCategory 实体
     */
    @ApiOperation("新增")
    @PostMapping("/create")
    public CommonResult create(@RequestBody SysRightCategory sysRightCategory) {
        if (sysRightCategory.getName() == null) {
            return CommonResult.failed(RightCategoryConstant.GET_RIGHT_CATEGORY_NAME_IS_NOT_NULL);
        }
        if (sysRightCategoryMapper.selectOne(new QueryWrapper<SysRightCategory>().eq("name", sysRightCategory.getName())) != null) {
            return CommonResult.failed(RightCategoryConstant.GET_RIGHT_CATEGORY_NAME_IS_EXIST);
        }
        boolean b = sysRightCategoryService.create(sysRightCategory);
        if (b == true) {
            return CommonResult.success(ResultCode.SUCCESS.getCode(), RightCategoryConstant.RIGHT_CATEGORY_ADD_IS_SUCCESS, b);
        }
        return CommonResult.failed(RightCategoryConstant.RIGHT_CATEGORY_ADD_IS_FAIL);
    }

    /**
     * @Description: 编辑
     * @param: id
     * @param: SysRightCategory 实体
     */
    @ApiOperation("编辑")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable(name = "id", required = true) Integer id,
                               @RequestBody SysRightCategory sysRightCategory) {
        if (sysRightCategory.getName() == null) {
            return CommonResult.failed(RightCategoryConstant.GET_RIGHT_CATEGORY_NAME_IS_NOT_NULL);
        }
        boolean b = sysRightCategoryService.update(id, sysRightCategory);
        if (b == true) {
            return CommonResult.success(ResultCode.SUCCESS.getCode(), RightCategoryConstant.RIGHT_CATEGORY_EDIT_IS_SUCCESS, b);
        }
        return CommonResult.failed(RightCategoryConstant.RIGHT_CATEGORY_EDIT_IS_FAIL);
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
                return CommonResult.success(ResultCode.SUCCESS.getCode(), RightCategoryConstant.DELETE_RIGHT_CATEGORY_SUCCESS, sysRightCategoryService.deleteById(id));
            }
            return CommonResult.failed(AdminConstants.CJ_ADMIN_NOT);
        }
        return CommonResult.failed(AdminConstants.QUAN_XIAN_NOT);
    }
}
