package com.admin.modules.right.service;

import com.admin.modules.right.vo.SysRightVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.entity.pojo.SysRight;

import java.util.List;

/**
 * @Description: 权限资源 service
 * @auther: xrq
 * @date: 2020/10/14 10:46
 */
public interface SysRightService {

    /**
     * @Description: 分页获取权限资源列表
     * @param: pageSize 每页大小
     * @param: pageNum 多少页
     * @param: name 资源名称
     * @param: url 资源URL
     * @param: categoryId 资源分类ID
     */
    Page<SysRightVo> queryList(Integer pageSize, Integer pageNum, String name, String url, Integer categoryId);

    /**
     * @Description: 新增
     * @param: SysRight 实体
     */
    boolean create(SysRight sysRight);

    /**
     * @Description: 编辑
     * @param: id
     * @param: SysRight 实体
     */
    boolean update(Integer id, SysRight sysRight);

    /**
     * @Description: 获取详情
     * @param: id
     */
    SysRight getItem(Integer id);

    /**
     * @Description: 删除
     * @param: id
     */
    boolean delete(Integer id);

    /**
     * @Description: 获取所有权限资源列表
     * @param:
     */
    List<SysRight> listAll();
}
