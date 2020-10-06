package com.admin.modules.login.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @Description: 登录参数
 * @auther: xrq
 * @date: 2020/9/14 21:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysAdminLoginDto {

    @NotEmpty
    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @NotEmpty
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
