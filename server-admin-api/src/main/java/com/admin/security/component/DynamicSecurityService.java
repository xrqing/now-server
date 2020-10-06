package com.admin.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @Description: 动态权限相关业务类
 * @auther: xrq
 * @date: 2020/9/10 21:06
 */
public interface DynamicSecurityService {
    /**
     * 加载资源ANT通配符和资源对应MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}
