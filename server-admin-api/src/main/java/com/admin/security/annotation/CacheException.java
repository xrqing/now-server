package com.admin.security.annotation;

import java.lang.annotation.*;

/**
 * @Description: 自定义注解，有该注解的缓存方法会抛出异常
 * @auther: xrq
 * @date: 2020/9/10 21:03
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}
