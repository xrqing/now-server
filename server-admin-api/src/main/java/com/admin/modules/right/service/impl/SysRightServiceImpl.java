package com.admin.modules.right.service.impl;

import com.admin.base.BaseServiceImpl;
import com.admin.modules.right.mapper.SysRightMapper;
import com.admin.modules.right.service.SysRightService;
import com.admin.modules.right.vo.SysRightVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.entity.pojo.SysRight;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 权限资源 serviceImpl
 * @auther: xrq
 * @date: 2020/10/14 10:46
 */
@Slf4j
@Service
public class SysRightServiceImpl extends BaseServiceImpl implements SysRightService {

    @Autowired
    private SysRightMapper sysRightMapper;

    /**
     * @Description: 分页获取权限资源列表
     * @param: pageSize 每页大小
     * @param: pageNum 多少页
     * @param: name 资源名称
     * @param: url 资源URL
     * @param: categoryId 资源分类ID
     */
    @Override
    public Page<SysRightVo> queryList(Integer pageSize, Integer pageNum, String name, String url, Integer categoryId) {
        return sysRightMapper.queryList(new Page<>(pageNum, pageSize), name, url, categoryId);
    }

    /**
     * @Description: 新增
     * @param: SysRight 实体
     */
    @Override
    public boolean create(SysRight sysRight) {
        sysRight.setCreateTime(new Date());
        int count = sysRightMapper.insert(sysRight);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * @Description: 编辑
     * @param: id
     * @param: SysRight 实体
     */
    @Override
    public boolean update(Integer id, SysRight sysRight) {
        sysRight.setId(id);
        int count = sysRightMapper.updateById(sysRight);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * @Description: 详情
     * @param: id
     */
    @Override
    public SysRight getItem(Integer id) {
        return sysRightMapper.selectById(id);
    }

    /**
     * @Description: 删除
     * @param: id
     */
    @Override
    public boolean delete(Integer id) {
        int count = sysRightMapper.deleteById(id);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * @Description: 获取所有权限资源列表
     * @param:
     */
    @Override
    public List<SysRight> listAll() {
        return sysRightMapper.selectList(new QueryWrapper<SysRight>());
    }
}
