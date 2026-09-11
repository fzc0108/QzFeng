package com.yzunlp.qzfeng.config;

import com.yzunlp.qzfeng.interceptor.SessionAdminInterceptor;
import com.yzunlp.qzfeng.interceptor.JwtTokenUserInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 配置类，注册web层相关组件
 */
@Slf4j
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    private final JwtTokenUserInterceptor jwtTokenUserInterceptor;

    @Autowired
    public WebMvcConfiguration(JwtTokenUserInterceptor jwtTokenUserInterceptor) {
        this.jwtTokenUserInterceptor = jwtTokenUserInterceptor;
    }

    /**
     * 设置拦截器
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //普通用户的拦截器（JwtToken模式）
        registry.addInterceptor(jwtTokenUserInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/info/login")
                .excludePathPatterns("/user/info/register")
                .excludePathPatterns("/user/health/admin/**")
                .excludePathPatterns("/user/propolis/admin/**")
                .excludePathPatterns("/user/eval/admin/**");

        // 管理员拦截器 (Session模式)
        registry.addInterceptor(new SessionAdminInterceptor())
                .addPathPatterns("/admin/**") // 拦截所有后台路径
                .excludePathPatterns(
                        "/admin/login",       // 必须放行：管理员登录接口
                        "/admin/logout",      // 可选：管理员退出接口
                        "/admin/admin-login.html",  // 必须放行：管理员登录页面
                        "/static/**",         // 放行静态资源目录
                        "/css/**",            // 放行样式
                        "/js/**",             // 放行脚本
                        "/images/**",         // 放行图片
                        "/favicon.ico"
                );
    }

    /**
     * 设置静态资源映射
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/images/**").addResourceLocations("file:/data/uploads/");


        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

}