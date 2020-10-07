package com.admin.modules.login.controller;

import com.admin.modules.admin.constants.AdminConstants;
import com.admin.modules.admin.mapper.SysAdminMapper;
import com.admin.modules.login.dto.SysAdminLoginDto;
import com.admin.modules.login.service.LoginService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.api.CommonResult;
import com.entity.pojo.SysAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private SysAdminMapper sysAdminMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @Description: 管理员登陆
     * @param: username 用户名
     * @param: password 密码
     */
    @ApiOperation(value = "管理员登陆")
    @PostMapping()
    public CommonResult login(@Validated @RequestBody SysAdminLoginDto sysAdminLoginDto) {
        SysAdmin exit_sysAdmin = sysAdminMapper.selectOne(new QueryWrapper<SysAdmin>().eq("username", sysAdminLoginDto.getUsername()));
        if (exit_sysAdmin == null) {
            return CommonResult.failed(AdminConstants.USERNAME_IS_ERROR);
        }
        if (!passwordEncoder.matches(sysAdminLoginDto.getPassword(), exit_sysAdmin.getPassword())) {
            return CommonResult.failed(AdminConstants.PASSWORD_IS_ERROR);
        }
        return CommonResult.success(loginService.login(sysAdminLoginDto.getUsername(), sysAdminLoginDto.getPassword()));
    }
}
