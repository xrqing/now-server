package com.admin.modules.admin.dto;

import lombok.Data;

/**
 * @Description: 编辑管理员参数 dto
 * @auther: xrq
 * @date: 2020/10/6 19:36
 */
@Data
public class EditAdminDto {

    /**
     * 用户名
     */
    private String username;

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
}
