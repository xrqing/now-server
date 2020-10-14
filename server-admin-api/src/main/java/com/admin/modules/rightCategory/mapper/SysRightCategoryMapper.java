package com.admin.modules.rightCategory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.entity.pojo.SysRightCategory;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * @Description: 权限资源分类 mapper
 * @auther: xrq
 * @date: 2020/10/14 12:02
 */
public interface SysRightCategoryMapper extends BaseMapper<SysRightCategory> {

    /**
     * @Description: 分页获取权限资源分类列表
     * @param: pageSize 每页大小
     * @param: pageNum 页数
     * @param: name 分类名称
     */
    Page<SysRightCategory> queryList(@Param("page") Page<SysRightCategory> page, @Param("name") String name);
}
