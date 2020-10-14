package com.admin.modules.rightCategory.service.impl;

import com.admin.base.BaseServiceImpl;
import com.admin.modules.rightCategory.mapper.SysRightCategoryMapper;
import com.admin.modules.rightCategory.service.SysRightCategoryService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.entity.pojo.SysRightCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description: 权限资源分类 serviceImpl
 * @auther: xrq
 * @date: 2020/10/14 12:02
 */
@Slf4j
@Service
public class SysRightCategoryServiceImpl extends BaseServiceImpl implements SysRightCategoryService {

    @Autowired
    private SysRightCategoryMapper sysRightCategoryMapper;

    /**
     * @Description: 分页获取权限资源分类列表
     * @param: pageSize 每页大小
     * @param: pageNum 页数
     * @param: name 分类名称
     */
    @Override
    public Page<SysRightCategory> queryList(Integer pageSize, Integer pageNum, String name) {
        return sysRightCategoryMapper.queryList(new Page<>(pageNum, pageSize), name);
    }

    /**
     * @Description: 新增
     * @param: SysRightCategory 实体
     */
    @Override
    public boolean create(SysRightCategory sysRightCategory) {
        sysRightCategory.setCreateTime(new Date());
        int count = sysRightCategoryMapper.insert(sysRightCategory);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * @Description: 新增
     * @param: id
     * @param: SysRightCategory 实体
     */
    @Override
    public boolean update(Integer id, SysRightCategory sysRightCategory) {
        sysRightCategory.setId(id);
        int count = sysRightCategoryMapper.updateById(sysRightCategory);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * @Description: 删除
     * @param: id
     */
    @Override
    public boolean deleteById(Integer id) {
        int count = sysRightCategoryMapper.deleteById(id);
        if (count > 0) {
            return true;
        }
        return false;
    }
}
