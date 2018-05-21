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
      String path = request.getRequestURI();

      ServiceResult<UserDo> result= (ServiceResult<UserDo>) request.getSession().getAttribute("user");
      if((path.contains("js")&&!path.contains(".jsp"))||path.contains("css")||path.contains("jpg")||path.contains("png")
          ||path.contains("/layui")){
        filterChain.doFilter(request,response);
        return;
      }
      if(path.contains("user")){
        filterChain.doFilter(request,response);
        return;
      }else{
        if(result==null){
          response.sendRedirect("../user/returnLogin");
          return;
        }else{
          filterChain.doFilter(request,response);
          return;
        }
      }
    }
}
