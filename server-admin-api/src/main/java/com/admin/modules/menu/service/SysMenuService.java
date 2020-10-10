package com.admin.modules.menu.service;

import com.admin.modules.menu.vo.SysMenuVo;

import java.util.List;

/**
 * @Description: 菜单 service
 * @auther: xrq
 * @date: 2020/10/9 10:20
 */
public interface SysMenuService {

    /**
     * @Description: 获取系统菜单左侧列表
     * @param:
     */
    List<SysMenuVo> menuList();
}
