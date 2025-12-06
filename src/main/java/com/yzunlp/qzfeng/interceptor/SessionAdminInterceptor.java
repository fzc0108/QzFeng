package com.yzunlp.qzfeng.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 管理员拦截器
 */
public class SessionAdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取 Session
        HttpSession session = request.getSession();

        // 2. 检查 Session 中是否有管理员的标记
        Object adminUser = session.getAttribute("ADMIN_SESSION");

        if (adminUser == null) {
            // 重定向到管理员登录页面
            response.sendRedirect("/admin/admin-login.html");

            // 返回 false 表示拦截请求，不再向下执行
            return false;
        }

        // 4. 如果有值，放行
        return true;
    }
}