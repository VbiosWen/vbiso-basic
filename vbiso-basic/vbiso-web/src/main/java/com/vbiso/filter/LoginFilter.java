package com.vbiso.filter;

import com.vbiso.utils.StringUtil;
import org.springframework.web.filter.HttpPutFormContentFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String path = req.getRequestURI();
        String password = (String) session.getAttribute("password");
        if (path.indexOf("login.jsp") > -1){
            filterChain.doFilter(req,resp);
            return;
        }else{
            if(StringUtil.isBlank(password)){
                resp.sendRedirect("/login.jsp");
            }else{
                filterChain.doFilter(req,resp);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
