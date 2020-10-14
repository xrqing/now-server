package com.admin.modules.right.mapper;

import com.admin.modules.right.vo.SysRightVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.entity.pojo.SysRight;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * @Description: 权限资源 mapper
 * @auther: xrq
 * @date: 2020/10/14 10:46
 */
public interface SysRightMapper extends BaseMapper<SysRight> {

    /**
     * @Description: 分页获取权限资源列表
     * @param: name 资源名称
     * @param: url 资源URL
     * @param: categoryId 资源分类ID
     */
    Page<SysRightVo> queryList(@Param("page") Page<SysRightVo> page, @Param("name") String name, @Param("url") String url, @Param("categoryId") Integer categoryId);
}
