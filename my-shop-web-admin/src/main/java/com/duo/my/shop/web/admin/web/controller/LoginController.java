package com.duo.my.shop.web.admin.web.controller;

import com.duo.my.shop.commons.util.ConstantUitls;
import com.duo.my.shop.domain.TbUser;

import com.duo.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping("user")
public class LoginController {

    //自动装配
    @Autowired
    private TbUserService tbUserService;
    /**
     * http://localhost:8080/user/login
     * 返回登录页面
     * @return
     */
    @RequestMapping(value={"","login"},method= RequestMethod.GET)
    public String login(){
        return "login";//   /WEB-INF/views/login.jsp
    }

    /**
     * 执行登录业务
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value="login",method=RequestMethod.POST)
    public String login(String email, String password, Model model, HttpServletRequest request){
        //实现登录
        TbUser user = tbUserService.login(email, password);
        if(user==null){
            //登录失败
            model.addAttribute("msg","用户名或密码错误");
            return login();
        }else{
            //登录成功
            request.getSession().setAttribute(ConstantUitls.SESSION_USER,user);
//            redirectAttributes.addFlashAttribute(ConstantUitls.SESSION_USER,user);
            return "redirect:/index";
        }
//        return "login";
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @RequestMapping(value="logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }


}
