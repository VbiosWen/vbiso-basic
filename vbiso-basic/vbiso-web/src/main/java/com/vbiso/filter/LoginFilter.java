package com.vbiso.filter;


import com.vbiso.domain.UserDo;
import com.vbiso.result.ServiceResult;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;

public class LoginFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
      String requestURI = request.getRequestURI();

      ServiceResult<UserDo> result= (ServiceResult<UserDo>) request.getSession().getAttribute("user");
      if(requestURI.contains(".css")||requestURI.contains(".js")||requestURI.contains(".jpg")){
        filterChain.doFilter(request,response);
        return;
      }
      if(requestURI.contains("/returnLogin")||requestURI.contains("login")||"/".equals(requestURI)){
        if(result==null&&requestURI.contains("/user/login")){
          filterChain.doFilter(request,response);
          return;
        }else if(result==null){
          request.getRequestDispatcher("/user/returnLogin").forward(request,response);
          return;
        }
        if(!result.isSuccess()){
          filterChain.doFilter(request,response);
          return;
        }else if(result.getData()==null){
          filterChain.doFilter(request,response);
        }
      }else{
        if (result==null){
          response.sendRedirect("../user/returnLogin");
        }else {
          filterChain.doFilter(request,response);
        }
      }
    }
}
