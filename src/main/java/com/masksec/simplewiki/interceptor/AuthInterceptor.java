package com.masksec.simplewiki.interceptor;

import cn.hutool.core.util.ObjectUtil;
import com.masksec.simplewiki.constant.Attribute;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 未登录用户，跳转至登录界面。
        Object user = request.getSession().getAttribute(Attribute.AUTH_USER_KEY);
        if (ObjectUtil.isEmpty(user)) {
            response.sendRedirect("/user/login");
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
