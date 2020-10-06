package com.admin.modules.admin.dto;

import lombok.Data;

/**
 * @Description: 新增管理员 dto
 * @auther: xrq
 * @date: 2020/10/6 16:22
 */
@Data
public class AddAdminDto {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 备注
     */
    private String note;
}
