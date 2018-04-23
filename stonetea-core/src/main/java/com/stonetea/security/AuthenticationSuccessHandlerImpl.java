package com.stonetea.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Transactional(readOnly=false,propagation= Propagation.REQUIRED,rollbackFor={Exception.class})
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        request.getSession().setAttribute("ss","1");
        //TODO 保存用户信息到内存.
        //TODO 在session中设置必要属性.
        //如果是使用spring session,可以结合起来.
    }

}