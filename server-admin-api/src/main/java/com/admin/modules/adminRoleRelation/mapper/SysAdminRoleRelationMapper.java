package com.admin.modules.adminRoleRelation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.pojo.SysAdminRoleRelation;
import org.springframework.data.repository.query.Param;

/**
 * @Description: 管理员与角色关系 mapper
 * @auther: xrq
 * @date: 2020/10/6 20:00
 */
public interface SysAdminRoleRelationMapper extends BaseMapper<SysAdminRoleRelation> {

    /**
     * @Description: 删除权限
     * @param: adminId 管理员id
     */
    void deleteByAdminId(@Param("adminId") Integer adminId);
}
