package com.admin.modules.role.controller;

import com.admin.modules.role.service.SysRoleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 角色管理 controller
 * @auther: xrq
 * @date: 2020/10/6 15:22
 */
@Slf4j
@RestController
@RequestMapping("/role")
@Api(tags = "SysRoleController", description = "角色管理")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
}
