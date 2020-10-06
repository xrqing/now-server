package com.admin.modules.login.service.impl;

import com.admin.modules.admin.mapper.SysAdminMapper;
import com.admin.modules.admin.service.SysAdminService;
import com.admin.modules.login.constants.LoginConstant;
import com.admin.modules.login.service.LoginService;
import com.admin.security.domain.AdminDetails;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.exception.Asserts;
import com.common.utils.JwtTokenUtil;
import com.entity.pojo.SysAdmin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Description: 管理员登录 service
 * @auther: xrq
 * @date: 2020/10/6 11:14
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysAdminMapper sysAdminMapper;

    @Autowired
    private SysAdminService sysAdminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * @Description: 管理员登陆
     * @param: username 用户名
     * @param: password 密码
     */
    @Override
    public HashMap login(String username, String password) {
        HashMap map = new HashMap<>();
        String token = null;
        try {
            //1： 管理员登录
            AdminDetails adminDetails = sysAdminService.loadAdminByUsername(username);
            if (!passwordEncoder.matches(password, adminDetails.getPassword())) {
                Asserts.fail(LoginConstant.PASSWORD_IS_ERROR);
            }
            if (!adminDetails.isEnabled()) {
                Asserts.fail(LoginConstant.NUMBER_IS_STOP);
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(adminDetails, null, adminDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(adminDetails);
            map.put("token", tokenHead + "" + token);
            SysAdmin exit_sysAdmin = sysAdminMapper.selectOne(new QueryWrapper<SysAdmin>().eq("username", username));
            map.put("id", exit_sysAdmin.getId());
            map.put("username", exit_sysAdmin.getUsername());
            map.put("icon", exit_sysAdmin.getIcon());
            map.put("email", exit_sysAdmin.getEmail());
            map.put("nickName", exit_sysAdmin.getNickName());
            map.put("note", exit_sysAdmin.getNote());
            map.put("status", exit_sysAdmin.getStatus());
            map.put("telephone", exit_sysAdmin.getTelephone());
            //2：记录登录记录 TODO
            //3：处理登录时间 TODO
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
