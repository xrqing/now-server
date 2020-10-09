package com.admin.modules.menu.service.impl;

import com.admin.modules.menu.mapper.SysMenuMapper;
import com.admin.modules.menu.service.SysMenuService;
import com.admin.modules.menu.vo.SysMenuVo;
import com.entity.pojo.SysMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
