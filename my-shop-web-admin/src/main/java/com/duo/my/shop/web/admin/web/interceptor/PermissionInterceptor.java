package com.duo.my.shop.web.admin.web.interceptor;

import com.duo.my.shop.commons.util.ConstantUitls;
import com.duo.my.shop.domain.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermissionInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        //  /login
        if(modelAndView!=null && modelAndView.getViewName()!=null && modelAndView.getViewName().endsWith("login")){
            //有没有登录
            TbUser user = (TbUser) request.getSession().getAttribute(ConstantUitls.SESSION_USER);
            if(user!=null){
                response.sendRedirect(request.getContextPath()+"/index");
            }
        }

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
