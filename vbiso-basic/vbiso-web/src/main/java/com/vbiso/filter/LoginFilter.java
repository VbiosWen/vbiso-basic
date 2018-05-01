package com.vbiso.filter;

import com.vbiso.domain.UserDo;
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
        String path=req.getRequestURI();
        HttpSession session = req.getSession();
        UserDo userInfo= (UserDo) session.getAttribute("user");
        if(path.contains("returnLogin")||path.contains("login")){
            filterChain.doFilter(req,resp);
            return;
        }else if(path.contains(".css")||path.contains(".js")||path.contains(".jpg")){
            filterChain.doFilter(req,resp);
        }
        else{
            if(userInfo==null){
               req.getRequestDispatcher("/user/returnLogin").forward(req,resp);
            }else{
                filterChain.doFilter(req,resp);
            }
        }


    }

    @Override
    public void destroy() {

    }
}
