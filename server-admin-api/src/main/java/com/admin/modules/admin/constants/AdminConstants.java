package com.admin.modules.admin.constants;

/**
 * @Description: 管理员通用常量
 * @auther: xrq
 * @date: 2020/10/6 15:25
 */
public class AdminConstants {

    /**
     * 管理员名称不能空
     */
    public static final String USERNAME_IS_NOT_NULL = "管理员名称不能空";

    /**
     * 管理员昵称不能空
     */
    public static final String NICKNAME_IS_NOT_NULL = "管理员昵称不能空";

    /**
     * 管理员邮箱不能空
     */
    public static final String EMAIL_IS_NOT_NULL = "管理员邮箱不能空";

    /**
     * 管理员密码不能为空
     */
    public static final String PASSWORD_IN_NOT_NULL = "管理员密码不能为空";

    /**
     * 管理员备注不能为空
     */
    public static final String NOTE_IS_NOT_NULL = "管理员备注不能为空";

    /**
     * 电话号码不能为空
     */
    public static final String TELEPHONE_IS_NOT_NULL = "电话号码不能为空";

    /**
     * 用户名已存在
     */
    public static final String ADD_SYS_ADMIN_IS_EXIST = "用户名已存在, 请重新输入！";

    /**
     * 管理员昵称已存在
     */
    public static final String NICKNAME_IS_EXIST = "管理员昵称已存在, 请重新输入！";

    /**
     * 用户名不正确
     */
    public static final String USERNAME_IS_ERROR = "您输入用户名不正确，请重新输入！";

    /**
     * 密码不正确
     */
    public static final String PASSWORD_IS_ERROR = "您输入密码不正确，请重新输入！";

    /**
     * 获取管理员列表成功
     */
    public static final String ADMIN_LIST_SUCCESS = "获取管理员列表成功";

    /**
     * 获取管理员列表失败
     */
    public static final String ADMIN_LIST_FAIL = "获取管理员列表失败";

    /**
     * 修改状态成功
     */
    public static final String CHANGE_STATUS_SUCCESS = "修改状态成功";

    /**
     * 修改状态失败,状态值只能为（0-停用， 1-正常）
     */
    public static final String CHANGE_STATUS_FAIL = "修改状态失败,状态值只能为（0-停用， 1-正常）";

    /**
     * 新增管理员成功
     */
    public static final String ADD_ADMIN_SUCCESS = "新增管理员成功";

    /**
     * 编辑管理员成功
     */
    public static final String EDIT_ADMIN_SUCCESS = "编辑管理员成功";

    /**
     * 编辑管理员失败
     */
    public static final String EDIT_ADMIN_FAIL = "编辑管理员失败";

    /**
     * 您不是超级管理员，您无权删除。
     */
    public static final String CJ_ADMIN_NOT = "您不是超级管理员，您无权删除。";

    /**
     * 删除管理员成功
     */
    public static final String DELETE_ADMIN_SUCCESS = "删除管理员成功";

    /**
     * 您暂时未授权，您无权删除。
     */
    public static final String QUAN_XIAN_NOT = "您暂时未授权，您无权删除。";
}
