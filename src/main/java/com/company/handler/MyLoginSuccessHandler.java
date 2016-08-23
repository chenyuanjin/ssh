package com.company.handler;

import com.company.entity.SysUsers;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyLoginSuccessHandler implements AuthenticationSuccessHandler {

    private static Logger logger = Logger.getLogger(MyLoginSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        SysUsers users = null;
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if (user != null && user instanceof SysUsers) {
            users = (SysUsers) user;
            httpServletResponse.sendRedirect("home");
            HttpSession session = httpServletRequest.getSession();
            String logid = users.getId();
            logger.debug("logid " + logid);
            if (logid != null && session != null) {
                session.setAttribute("USER_LOGIN_SUCCESS_ID", logid);
                session.setAttribute("username", ((SysUsers) user).getUserName());
            }
        }
    }

}
