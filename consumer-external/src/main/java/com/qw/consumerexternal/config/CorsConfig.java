//package com.qw.consumerexternal.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @Program: cloud-blog
// * @ClassName CorsConfig
// * @Description:
// * @Author: Eclair
// * @Create: 2020-03-08 18:55
// * @Version 1.0
// **/
//
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//
//    /**
//     * @Author Eclair
//     * @Description //解决跨域问题
//     * @Date 2020/3/11 16:13
//     * @Param @param registry:
//     * @return void
//     **/
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowCredentials(true)
//                .allowedMethods("GET", "POST", "DELETE", "PUT")
//                .maxAge(3600);
//    }
//}