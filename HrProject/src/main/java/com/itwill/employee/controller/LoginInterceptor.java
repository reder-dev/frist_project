package com.itwill.employee.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        
        // 로그인 페이지는 인터셉터에서 제외
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/login") || requestURI.contains("/resources/")) {
            return true;
        }
        
        // 로그인 상태가 아니면 로그인 페이지로 리다이렉트
        if (loggedIn == null || !loggedIn) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 필요한 경우 구현
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 필요한 경우 구현
    }
}