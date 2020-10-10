package com.admin.modules.menu.mapper;

import com.admin.modules.menu.vo.SysMenuVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.pojo.SysMenu;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * @Description: 菜单 mapper
 * @auther: xrq
 * @date: 2020/10/9 10:19
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * @Description: 查询父级菜单列表
     * @param:
     */
    List<SysMenuVo> treeList();

    /**
     * @Description: 查询子菜单列表
     * @param: id
     */
    List<SysMenuVo> childrenList(@Param("id") Integer id);
}
