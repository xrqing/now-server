package com.admin.modules.menu.controller;

import com.admin.base.BaseController;
import com.admin.modules.adminRoleRelation.mapper.SysAdminRoleRelationMapper;
import com.admin.modules.menu.constants.MenuConstant;
import com.admin.modules.menu.mapper.SysMenuMapper;
import com.admin.modules.menu.service.SysMenuService;
import com.admin.modules.menu.vo.SysMenuVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;;
import com.common.api.CommonResult;
import com.common.api.ResultCode;
import com.entity.pojo.SysAdminRoleRelation;
import com.entity.pojo.SysMenu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 菜单 controller
 * @auther: xrq
 * @date: 2020/10/9 10:22
 */
@Slf4j
@RestController
@RequestMapping("/menu")
@Api(tags = "SysMenuController", description = "菜单管理")
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysAdminRoleRelationMapper sysAdminRoleRelationMapper;

    /**
     * @Description: 获取系统菜单左侧列表
     * @param:
     */
    @ApiOperation("获取系统菜单左侧列表")
    @GetMapping("/menuList")
    public CommonResult menuList() {
        List<SysMenuVo> menuVoList = sysMenuService.menuList();
        if (menuVoList != null) {
            return CommonResult.success(ResultCode.SUCCESS.getCode(), MenuConstant.MNEU_LIST_SUCCESS, menuVoList);
        } else {
            return CommonResult.failed(MenuConstant.MNEU_LIST_FAIL);
        }
    }

    /**
     * @Description: 新增菜单
     * @param: SysMenu 菜单实体
     */
    @ApiOperation("新增菜单")
    @PostMapping("/create")
    public CommonResult create(@RequestBody SysMenu sysMenu) {
        SysMenu exit_sysMenu = sysMenuMapper.selectOne(new QueryWrapper<SysMenu>().eq("menu_name", sysMenu.getMenuName()));
        if (exit_sysMenu == null) {
            boolean b = sysMenuService.create(sysMenu);
            if (b == true) {
                return CommonResult.success(ResultCode.SUCCESS.getCode(), MenuConstant.ADD_MNEU_SUCCESS, b);
            } else {
                return CommonResult.failed(MenuConstant.ADD_MNEU_FAIL);
            }
        }
        return CommonResult.failed(MenuConstant.ADD_MNEU_FAIL_MENU_NAME_IS_EXIST);
    }

    /**
     * @Description: 修改菜单
     * @param: id
     * @param: SysMenu 实体
     */
    @ApiOperation("修改菜单")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable(name = "id", required = true) Integer id,
                               @RequestBody SysMenu sysMenu) {
        boolean b = sysMenuService.update(id, sysMenu);
        if (b == true) {
            return CommonResult.success(ResultCode.SUCCESS.getCode(), MenuConstant.EDIT_MNEU_SUCCESS, b);
        }
        return CommonResult.success(MenuConstant.EDIT_MNEU_FAIL);
    }

    /**
     * @Description: 根据菜单id获取详情
     * @param: id
     */
    @ApiOperation("根据菜单id获取详情")
    @GetMapping("/{id}")
    public CommonResult getItem(@PathVariable(name = "id", required = true) Integer id) {
        return CommonResult.success(ResultCode.SUCCESS.getCode(), MenuConstant.MNEU_DETAIL_BY_ID, sysMenuService.getItem(id));
    }

    /**
     * @Description: 根据ID删除后台菜单
     * @param: id
     */
    @ApiOperation("根据ID删除后台菜单")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable(name = "id", required = true) Integer id) {
        SysAdminRoleRelation exit_sysAdminRoleRelation = sysAdminRoleRelationMapper.selectOne(new QueryWrapper<SysAdminRoleRelation>().eq("admin_id", getAdminId()));
        if (exit_sysAdminRoleRelation != null) {
            if (exit_sysAdminRoleRelation.getRoleId() == 1) {
                return CommonResult.success(ResultCode.SUCCESS.getCode(), MenuConstant.DELETE_EMNU_SUCCESS, sysMenuService.delete(id));
            }
            return CommonResult.failed(MenuConstant.CJ_ADMIN_NOT);
        }
        return CommonResult.failed(MenuConstant.QUAN_XIAN_NOT);
    }

    /**
     * @Description: 获取菜单列表
     * @param: menuName 菜单名称
     */
    @ApiOperation("获取菜单列表")
    @GetMapping("/list")
    public CommonResult selectMenuList(@RequestParam(name = "menuName", required = false) String menuName) {
        List<SysMenuVo> list = sysMenuService.selectMenuList(menuName);
        if (list != null) {
            return CommonResult.success(ResultCode.SUCCESS.getCode(), MenuConstant.EMNU_LIST_SUCCESS, list);
        }
        return CommonResult.failed(MenuConstant.EMNU_LIST_FAIL);
    }

    /**
     * @Description: 修改菜单的状态
     * @param: id
     * @param: hidden
     */
    @ApiOperation("修改菜单的状态")
    @PostMapping("/updateHidden/{id}/{hidden}")
    public CommonResult updateHidden(@PathVariable(name = "id", required = true) Integer id,
                                     @PathVariable(name = "hidden", required = true) Integer hidden) {
        boolean b = sysMenuService.updateHidden(id, hidden);
        if (b == true) {
            return CommonResult.success(ResultCode.SUCCESS.getCode(), MenuConstant.UPDATE_STATUS_SUCCESS, b);
        }
        return CommonResult.failed(MenuConstant.UPDATE_STATUS_FAIL);
    }

    /**
     * @Description: 菜单下拉选择
     * @param:
     */
    @ApiOperation("菜单下拉选择")
    @GetMapping("/selectList")
    public CommonResult selectList() {
        return CommonResult.success(sysMenuService.selectList());
    }
}
