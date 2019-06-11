/*
package com.pzy.study.base.commons;

import com.pzy.study.base.commons.utils.RequestHolder;
import com.pzy.study.entity.UserEntity;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

*/
/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-25
 *//*

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");
        if (null == userEntity){
            String path = "login.html";
            response.sendRedirect("path");
            return;
        }
        RequestHolder.add(userEntity);
        RequestHolder.add(request);
        filterChain.doFilter(request ,response);
        return;
    }

    @Override
    public void destroy() {

    }
}
*/
