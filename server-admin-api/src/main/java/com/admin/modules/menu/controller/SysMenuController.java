package com.admin.modules.menu.controller;

import com.admin.modules.menu.constants.MenuConstant;
import com.admin.modules.menu.service.SysMenuService;
import com.admin.modules.menu.vo.SysMenuVo;
import com.common.api.CommonResult;
import com.common.api.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.smartcardio.CommandAPDU;
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
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

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
}
