package com.admin.config;

import com.common.config.BaseSwaggerConfig;
import com.common.config.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: Swagger API文档相关配置
 * @auther: xrq
 * @date: 2020/9/10 20:59
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.admin.modules")
                .title("体育后台管理系统")
                .description("体育后台管理系统相关接口文档")
                .contactName("xrq")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
