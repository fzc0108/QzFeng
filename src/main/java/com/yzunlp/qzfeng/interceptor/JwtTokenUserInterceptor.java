package com.yzunlp.qzfeng.interceptor;


import com.yzunlp.qzfeng.common.BaseContext;
import com.yzunlp.qzfeng.common.JwtProperties;
import com.yzunlp.qzfeng.common.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * jwt令牌校验的拦截器用户端
 */
@Slf4j
@Component
public class JwtTokenUserInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getUserTokenName());
        log.info("拦截器:user-token:{}", token);

        //2、校验令牌
        try {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
            Long userId = Long.valueOf(claims.get("userID").toString());
            BaseContext.setCurrentId(userId);   // 通过ThreadLocal获取id，在jwt的claim的时候，把id放进去过了
            log.info("拦截器:user-token:解析(user-id:{})", userId);
            //3、通过，放行
            return true;
        } catch (Exception ex) {
            log.info("拦截器: user-token: 解析失败!");
            //4、不通过，响应 401 状态码
            response.setStatus(401);
            return false;
        }
    }
}
