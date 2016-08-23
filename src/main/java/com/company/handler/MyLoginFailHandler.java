package com.company.handler;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyLoginFailHandler implements AuthenticationFailureHandler {

    private static Logger logger = Logger.getLogger(MyLoginFailHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpservletrequest,
                                        HttpServletResponse httpservletresponse,
                                        AuthenticationException authenticationexception)
            throws IOException, ServletException {
        logger.error("登录失败");
    }

}
