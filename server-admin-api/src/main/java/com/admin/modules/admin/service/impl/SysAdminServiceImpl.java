package com.admin.modules.admin.service.impl;

import com.admin.modules.admin.constants.AdminConstants;
import com.admin.modules.admin.dto.AddAdminDto;
import com.admin.modules.admin.dto.EditAdminDto;
import com.admin.modules.admin.mapper.SysAdminMapper;
import com.admin.modules.admin.service.SysAdminService;
import com.admin.modules.admin.vo.SysAdminDetailVo;
import com.admin.modules.admin.vo.SysAdminVo;
import com.admin.modules.adminRoleRelation.mapper.SysAdminRoleRelationMapper;
import com.admin.modules.login.constants.LoginConstant;
import com.admin.security.domain.AdminDetails;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.exception.Asserts;
import com.common.utils.StringUtils;
import com.entity.pojo.SysAdmin;
import com.entity.pojo.SysAdminRoleRelation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description: 管理员 serviceImpl
 * @auther: xrq
 * @date: 2020/10/6 11:19
 */
@Slf4j
@Service
public class SysAdminServiceImpl implements SysAdminService {

    @Autowired
    private SysAdminMapper sysAdminMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysAdminRoleRelationMapper sysAdminRoleRelationMapper;

    /**
     * @Description: 获取管理员信息
     * @param: username 用户名
     */
    @Override
    public AdminDetails loadAdminByUsername(String username) {
        SysAdmin exit_sysAdmin = sysAdminMapper.selectOne(new QueryWrapper<SysAdmin>().eq("username", username));
        if (exit_sysAdmin != null) {
            return new AdminDetails(exit_sysAdmin);
        }
        throw new UsernameNotFoundException(LoginConstant.USERNAME_OR_PASSWORD_IS_ERROR);
    }

    /**
     * @Description: 根据用户名分页查询管理员
     * @param: username 管理员名称
     * @param: pageSize 每页大小
     * @param: pageNum 多少页
     */
    @Override
    public Page<SysAdminVo> getPageList(Integer pageSize, Integer pageNum, String username) {
        return sysAdminMapper.getPageList(new Page<>(pageNum, pageSize), username);
    }

    /**
     * @Description: 新增管理员
     * @param: AddAdminDto 新增参数
     */
    @Override
    public SysAdmin create(AddAdminDto adminDto) {
        //参数校验
        if (StringUtils.isBlank(adminDto.getUsername())) {
            Asserts.fail(AdminConstants.USERNAME_IS_NOT_NULL);
        }
        if (StringUtils.isBlank(adminDto.getNickName())) {
            Asserts.fail(AdminConstants.NICKNAME_IS_NOT_NULL);
        }
        if (StringUtils.isBlank(adminDto.getEmail())) {
            Asserts.fail(AdminConstants.EMAIL_IS_NOT_NULL);
        }
        if (StringUtils.isBlank(adminDto.getTelephone())) {
            Asserts.fail(AdminConstants.TELEPHONE_IS_NOT_NULL);
        }
        if (StringUtils.isBlank(adminDto.getPassword())) {
            Asserts.fail(AdminConstants.PASSWORD_IN_NOT_NULL);
        }
        if (StringUtils.isBlank(adminDto.getNote())) {
            Asserts.fail(AdminConstants.NOTE_IS_NOT_NULL);
        }
        //判断用户名和昵称是否相同
        if (sysAdminMapper.selectOne(new QueryWrapper<SysAdmin>().eq("username", adminDto.getUsername())) != null) {
            Asserts.fail(AdminConstants.ADD_SYS_ADMIN_IS_EXIST);
        }
        if (sysAdminMapper.selectOne(new QueryWrapper<SysAdmin>().eq("nick_name", adminDto.getNickName())) != null) {
            Asserts.fail(AdminConstants.NICKNAME_IS_EXIST);
        }
        //入库
        SysAdmin sysAdmin = new SysAdmin();
        BeanUtils.copyProperties(adminDto, sysAdmin);
        sysAdmin.setCreateTime(new Date());
        sysAdmin.setStatus(1);
        String encodePassword = passwordEncoder.encode(sysAdmin.getPassword());
        sysAdmin.setPassword(encodePassword);
        sysAdminMapper.insert(sysAdmin);
        return sysAdmin;
    }

    /**
     * @Description: 修改管理员状态
     * @param: id
     * @param: status 状态（0-禁用， 1-启用）
     */
    @Override
    public boolean updateStatus(Integer id, Integer status) {
        if (status == 0 || status == 1) {
            SysAdmin sysAdmin = new SysAdmin();
            sysAdmin.setId(id);
            sysAdmin.setStatus(status);
            int count = sysAdminMapper.updateById(sysAdmin);
            if (count <= 0) {
                Asserts.fail(AdminConstants.STATUS_IS_UPDATE_FAIL);
            }
            return true;
        }
        return false;
    }

    /**
     * @Description: 根据id查询管理员信息
     * @param: id
     */
    @Override
    public SysAdminDetailVo getDetail(Integer id) {
        return sysAdminMapper.getDetail(id);
    }

    /**
     * @Description: 编辑
     * @param: editAdminDto
     */
    @Override
    public boolean editSysAdmin(Integer id, EditAdminDto editAdminDto) {
        SysAdmin edit_sysAdmin = new SysAdmin();
        edit_sysAdmin.setId(id);
        edit_sysAdmin.setUsername(editAdminDto.getUsername());
        edit_sysAdmin.setNickName(editAdminDto.getNickName());
        edit_sysAdmin.setEmail(editAdminDto.getEmail());
        edit_sysAdmin.setTelephone(editAdminDto.getTelephone());
        int count = sysAdminMapper.updateById(edit_sysAdmin);
        if (count <= 0) {
            return false;
        }
        return true;
    }

    /**
     * @Description: 删除
     * @param: id
     */
    @Override
    public boolean deleteById(Integer id) {
        int count = sysAdminMapper.deleteById(id);
        if (count <= 0) {
            return false;
        }
        return true;
    }

    /**
     * @Description: 分配角色
     * @param: adminId 管理员id
     * @param: roleId 角色id
     */
    @Override
    public boolean updateRole(Integer adminId, Integer roleId) {
        SysAdminRoleRelation exit_sysAdminRoleRelation = sysAdminRoleRelationMapper.selectById(adminId);
        if (exit_sysAdminRoleRelation != null) {
            exit_sysAdminRoleRelation.setAdminId(adminId);
            exit_sysAdminRoleRelation.setRoleId(roleId);
            int count = sysAdminRoleRelationMapper.updateById(exit_sysAdminRoleRelation);
            if (count <= 0) {
                return false;
            }
        }
        return true;
    }
}
