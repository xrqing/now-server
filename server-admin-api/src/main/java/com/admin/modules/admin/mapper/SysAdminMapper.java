package com.admin.modules.admin.mapper;

import com.admin.modules.admin.vo.SysAdminDetailVo;
import com.admin.modules.admin.vo.SysAdminVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.entity.pojo.SysAdmin;
import org.springframework.data.repository.query.Param;

/**
 * @Description: 管理员 mapper
 * @auther: xrq
 * @date: 2020/10/6 11:19
 */
public interface SysAdminMapper extends BaseMapper<SysAdmin> {

    /**
     * @Description: 根据用户名分页查询管理员
     * @param: username 管理员名称
     */
    Page<SysAdminVo> getPageList(@Param("page") Page<SysAdminVo> page, @Param("username") String username);

    /**
     * @Description: 根据id查询管理员信息
     * @param: id
     */
    SysAdminDetailVo getDetail(@Param("id") Integer id);
}
