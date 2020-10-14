package com.admin.modules.right.controller;

import com.admin.base.BaseController;
import com.admin.modules.admin.constants.AdminConstants;
import com.admin.modules.adminRoleRelation.mapper.SysAdminRoleRelationMapper;
import com.admin.modules.right.constants.RightsConstant;
import com.admin.modules.right.mapper.SysRightMapper;
import com.admin.modules.right.service.SysRightService;
import com.admin.modules.right.vo.SysRightVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.api.CommonPage;
import com.common.api.CommonResult;
import com.common.api.ResultCode;
import com.entity.pojo.SysAdminRoleRelation;
import com.entity.pojo.SysRight;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 权限资源 controller
 * @auther: xrq
 * @date: 2020/10/14 10:46
 */
@Slf4j
@RestController
@RequestMapping("/right")
@Api(tags = "SysRightController", description = "权限资源管理")
public class SysRightController extends BaseController {

    @Autowired
    private SysRightService sysRightService;

    @Autowired
    private SysRightMapper sysRightMapper;

    @Autowired
    private SysAdminRoleRelationMapper sysAdminRoleRelationMapper;

    /**
     * @Description: 分页获取权限资源列表
     * @param: pageSize 每页大小
     * @param: pageNum 多少页
     * @param: name 资源名称
     * @param: url 资源URL
     * @param: categoryId 资源分类ID
     */
    @ApiOperation("分页获取权限资源列表")
    @GetMapping("/list")
    public CommonResult queryList(@RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name = "name", required = false) String name,
                                  @RequestParam(name = "url", required = false) String url,
                                  @RequestParam(name = "categoryId", required = false) Integer categoryId) {
        Page<SysRightVo> list = sysRightService.queryList(pageSize, pageNum, name, url, categoryId);
        if (list != null) {
            return CommonResult.success(ResultCode.SUCCESS.getCode(), RightsConstant.GET_RIGHT_LIST_SUCCESS, CommonPage.restPage(list));
        }
        return CommonResult.failed(RightsConstant.GET_RIGHT_LIST_FAIL);
    }

    /**
     * @Description: 新增
     * @param: SysRight 实体
     */
    @ApiOperation("新增")
    @PostMapping("/create")
    public CommonResult create(@RequestBody SysRight sysRight) {
        if (sysRight.getName() == null) {
            return CommonResult.failed(RightsConstant.ADD_SYS_RIGHT_NAME_IS_NOT_NULL);
        }
        if (sysRight.getUrl() == null) {
            return CommonResult.failed(RightsConstant.ADD_SYS_RIGHT_URL_IS_NOT_NULL);
        }
        if (sysRight.getCategoryId() == null) {
            return CommonResult.failed(RightsConstant.ADD_SYS_RIGHT_ID_IS_NOT_NULL);
        }
        if (sysRight.getDescription() == null) {
            return CommonResult.failed(RightsConstant.RIGHT_DESCRIPTION_IS_NOT_NULL);
        }
        if (sysRightMapper.selectOne(new QueryWrapper<SysRight>().eq("name", sysRight.getName())) != null) {
            return CommonResult.failed(RightsConstant.ADD_SYS_RIGHT_NAME_IS_EXIST);
        }
        boolean b = sysRightService.create(sysRight);
        if (b == true) {
            return CommonResult.success(ResultCode.SUCCESS.getCode(), RightsConstant.RIGHT_ADD_IS_SUCCESS, b);
        }
        return CommonResult.failed(RightsConstant.RIGHT_ADD_IS_FAIL);
    }

    /**
     * @Description: 编辑
     * @param: id
     * @param: SysRight 实体
     */
    @ApiOperation("编辑")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable(name = "id", required = true) Integer id,
                               @RequestBody SysRight sysRight) {
        boolean b = sysRightService.update(id, sysRight);
        if (b == true) {
            return CommonResult.success(ResultCode.SUCCESS.getCode(), RightsConstant.RIGHT_EDIT_IS_SUCCESS, b);
        }
        return CommonResult.failed(RightsConstant.RIGHT_EDIT_IS_FAIL);
    }

    /**
     * @Description: 获取详情
     * @param: id
     */
    @ApiOperation("获取详情")
    @GetMapping("/{id}")
    public CommonResult getItem(@PathVariable(name = "id", required = true) Integer id) {
        return CommonResult.success(sysRightService.getItem(id));
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
                return CommonResult.success(ResultCode.SUCCESS.getCode(), RightsConstant.DELETE_RIGHT_SUCCESS, sysRightService.delete(id));
            }
            return CommonResult.failed(AdminConstants.CJ_ADMIN_NOT);
        }
        return CommonResult.failed(AdminConstants.QUAN_XIAN_NOT);
    }

    /**
     * @Description: 获取所有权限资源列表
     * @param:
     */
    @ApiOperation("获取所有权限资源列表")
    @PostMapping("/listAll")
    public CommonResult listAll() {
        return CommonResult.success(sysRightService.listAll());
    }
}

