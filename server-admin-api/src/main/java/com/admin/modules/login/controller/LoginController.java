package com.admin.modules.login.controller;

import com.admin.modules.login.dto.SysAdminLoginDto;
import com.admin.modules.login.service.LoginService;
import com.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 管理员登录 controller
 * @auther: xrq
 * @date: 2020/10/6 13:03
 */
@Slf4j
@RestController
@RequestMapping("/login")
@Api(tags = "LoginController", description = "管理员登录")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * @Description: 管理员登陆
     * @param: username 用户名
     * @param: password 密码
     */
    @ApiOperation(value = "管理员登陆")
    @PostMapping()
    public CommonResult login(@Validated @RequestBody SysAdminLoginDto sysAdminLoginDto) {
        return CommonResult.success(loginService.login(sysAdminLoginDto.getUsername(), sysAdminLoginDto.getPassword()));
    }
}
