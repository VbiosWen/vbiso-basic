package com.vbiso.filter;

import com.vbiso.utils.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      ServletContext servletContext = filterConfig.getServletContext();
      String contextPath = servletContext.getContextPath();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String path = req.getRequestURI();
        String userInfo= (String) session.getAttribute("user");
        if (path.indexOf("login.jsp") > -1){
            filterChain.doFilter(req,resp);
            return;
        }else{
            if(StringUtil.isBlank(userInfo)){
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
