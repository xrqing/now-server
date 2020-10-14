package com.admin.modules.menu.service.impl;

import com.admin.modules.menu.mapper.SysMenuMapper;
import com.admin.modules.menu.service.SysMenuService;
import com.admin.modules.menu.vo.MenuSelectVo;
import com.admin.modules.menu.vo.SysMenuVo;
import com.entity.pojo.SysMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 菜单 serviceImpl
 * @auther: xrq
 * @date: 2020/10/9 10:21
 */
@Slf4j
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * @Description: 获取系统菜单左侧列表
     * @param:
     */
    @Override
    public List<SysMenuVo> menuList() {
        List<SysMenuVo> list = sysMenuMapper.treeList();
        list.forEach(sysMenuVo -> {
            List<SysMenuVo> childrenList = sysMenuMapper.childrenList(sysMenuVo.getId());
            sysMenuVo.setChildren(childrenList);
        });
        return list;
    }

    /**
     * @Description: 新增菜单
     * @param: SysMenu 菜单实体
     */
    @Override
    public boolean create(SysMenu sysMenu) {
        //一级菜单
        if (sysMenu.getParentId() == 0) {
            sysMenu.setCreateTime(new Date());
            sysMenu.setLevel(0);
        } else {
            //二级菜单
            SysMenu exit_sysMenu = sysMenuMapper.selectById(sysMenu.getParentId());
            if (exit_sysMenu != null) {
                sysMenu.setCreateTime(new Date());
                sysMenu.setLevel(exit_sysMenu.getLevel() + 1);
            } else {
                sysMenu.setLevel(0);
            }
        }
        int count = sysMenuMapper.insert(sysMenu);
        if (count <= 0) {
            return false;
        }
        return true;
    }

    /**
     * @Description: 修改菜单
     * @param: id
     * @param: SysMenu 实体
     */
    @Override
    public boolean update(Integer id, SysMenu sysMenu) {
        sysMenu.setId(id);
        if (sysMenu.getParentId() == 0) {
            sysMenu.setLevel(0);
        } else {
            SysMenu exit_sysMenu = sysMenuMapper.selectById(sysMenu.getParentId());
            if (exit_sysMenu != null) {
                sysMenu.setLevel(exit_sysMenu.getLevel() + 1);
            } else {
                sysMenu.setLevel(0);
            }
        }
        int count = sysMenuMapper.updateById(sysMenu);
        if (count <= 0) {
            return false;
        }
        return true;
    }

    /**
     * @Description: 根据菜单id获取详情
     * @param: id
     */
    @Override
    public SysMenu getItem(Integer id) {
        return sysMenuMapper.selectById(id);
    }

    /**
     * @Description: 根据ID删除后台菜单
     * @param: id
     */
    @Override
    public boolean delete(Integer id) {
        int count = sysMenuMapper.deleteById(id);
        if (count <= 0) {
            return false;
        }
        return true;
    }

    /**
     * @Description: 获取菜单列表
     * @param: menuName 菜单名称
     */
    @Override
    public List<SysMenuVo> selectMenuList(String menuName) {
        List<SysMenuVo> menuList = sysMenuMapper.selectMenuList(menuName);
        menuList.forEach(sysMenu -> {
            List<SysMenuVo> childrenList = sysMenuMapper.childrenList(sysMenu.getId());
            sysMenu.setChildren(childrenList);
        });
        return menuList;
    }

    /**
     * @Description: 修改菜单的状态
     * @param: id
     * @param: hidden
     */
    @Override
    public boolean updateHidden(Integer id, Integer hidden) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(id);
        sysMenu.setHidden(hidden);
        int count = sysMenuMapper.updateById(sysMenu);
        if (count <= 0) {
            return false;
        }
        return true;
    }

    /**
     * @Description: 菜单下拉选择
     * @param:
     */
    @Override
    public List<MenuSelectVo> selectList() {
        return sysMenuMapper.selectList();
    }
}
