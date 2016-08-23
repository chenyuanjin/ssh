package com.company.controller;

import com.company.entity.SysUsers;
import com.company.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by chenyj on 16/8/23.
 */
public class LoginController {
    private static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error",required = false,defaultValue = "0")String error,
                              HttpServletResponse response, HttpServletRequest request) {
        boolean isAjax = "XMLHttpRequest".equals(request
                .getHeader("X-Requested-With"));
        // 如果是ajax请求
        if (isAjax) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        ModelAndView modelAndView = new ModelAndView("login");
        if("0".equals(error))
            modelAndView.addObject("error",false);
        else
            modelAndView.addObject("error",true);

        return modelAndView;
    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String toMainPage(Map model, HttpSession session){

        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!StringUtil.isNotEmpty(user)){
            return "login";
        }

        if(user != null && user instanceof SysUsers){
            SysUsers user1 = (SysUsers) user;
            model.put("username",user1.getUserName());
            session.setAttribute("user",user1);
        }
        return "home";
    }
}
