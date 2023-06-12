package com.project.managementsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/************************
 * Manage-System
 * com.project.managementsystem.config
 * MHC
 * author : mhc
 * date:  2023/6/12 13:07
 * description : 跨域配置类（但是为啥不要跨域也能访问）
 ************************/
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                //允许跨域的域名，可以用*表示允许任何域名使用
                .allowedOriginPatterns("*")
                //是否允许携带cookie
                .allowCredentials(true)
                .exposedHeaders("*");//
    }
}
