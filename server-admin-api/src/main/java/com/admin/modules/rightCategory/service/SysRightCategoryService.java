package com.admin.modules.rightCategory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.entity.pojo.SysRightCategory;

import java.util.List;

/**
 * @Description: 权限资源分类 service
 * @auther: xrq
 * @date: 2020/10/14 12:02
 */
public interface SysRightCategoryService {

    /**
     * @Description: 分页获取权限资源分类列表
     * @param: pageSize 每页大小
     * @param: pageNum 页数
     * @param: name 分类名称
     */
    Page<SysRightCategory> queryList(Integer pageSize, Integer pageNum, String name);

    /**
     * @Description: 新增
     * @param: SysRightCategory 实体
     */
    boolean create(SysRightCategory sysRightCategory);

    /**
     * @Description: 新增
     * @param: id
     * @param: SysRightCategory 实体
     */
    boolean update(Integer id, SysRightCategory sysRightCategory);

    /**
     * @Description: 删除
     * @param: id
     */
    boolean deleteById(Integer id);

    /**
     * @Description: 根据id查询详情
     * @param: id
     */
    SysRightCategory getItem(Integer id);

    /**
     * @Description: 查询全部分类
     * @param:
     */
    List<SysRightCategory> listAll();
}
