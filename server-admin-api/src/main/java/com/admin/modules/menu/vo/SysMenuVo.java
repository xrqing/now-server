package com.admin.modules.menu.vo;

import com.entity.pojo.SysMenu;
import lombok.Data;

import java.util.List;

/**
 * @Description: 系统菜单左侧数据 vo
 * @auther: xrq
 * @date: 2020/10/9 10:24
 */
@Data
public class SysMenuVo extends SysMenu {

    /**
     * 子集菜单
     */
    List<SysMenuVo> children;
}
