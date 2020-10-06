package com.admin.modules.admin.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 管理员详情对象 vo
 * @auther: xrq
 * @date: 2020/10/6 16:10
 */
@Data
public class SysAdminDetailVo {

    /**
     * id
     */
    private Integer id;

    /**
     * 管理员名称
     */
    private String username;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 头像
     */
    private String icon;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 备注信息
     */
    private String note;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后登陆时间
     */
    private Date loginTime;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    private Integer status;

    /**
     * 角色id
     */
    private Integer roleId;
}
