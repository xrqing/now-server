package com.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 体育后台管理系统启动类
 * @auther: xrq
 * @date: 2020/9/10 20:03
 */
@Slf4j
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        log.info("体育后台管理系统启动成功！");
    }
}
