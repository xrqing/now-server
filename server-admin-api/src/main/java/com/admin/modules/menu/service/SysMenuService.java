package com.admin.modules.menu.service;

import com.admin.modules.menu.vo.MenuSelectVo;
import com.admin.modules.menu.vo.SysMenuVo;
import com.entity.pojo.SysMenu;

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

    /**
     * @Description: 新增菜单
     * @param: SysMenu 菜单实体
     */
    boolean create(SysMenu sysMenu);

    /**
     * @Description: 修改菜单
     * @param: id
     * @param: SysMenu 实体
     */
    boolean update(Integer id, SysMenu sysMenu);

    /**
     * @Description: 根据菜单id获取详情
     * @param: id
     */
    SysMenu getItem(Integer id);

    /**
     * @Description: 根据ID删除后台菜单
     * @param: id
     */
    boolean delete(Integer id);

    /**
     * @Description: 分获取菜单列表
     * @param: menuName 菜单名称
     */
    List<SysMenuVo> selectMenuList(String menuName);

    /**
     * @Description: 修改菜单的状态
     * @param: id
     * @param: hidden
     */
    boolean updateHidden(Integer id, Integer hidden);

    /**
     * @Description: 菜单下拉选择
     * @param:
     */
    List<MenuSelectVo> selectList();
}
