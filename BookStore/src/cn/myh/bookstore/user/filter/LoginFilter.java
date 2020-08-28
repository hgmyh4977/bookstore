package cn.myh.bookstore.user.filter;

import cn.myh.bookstore.user.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description: ${NAME} <br>
 * date: 2020/8/7 23:44 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class LoginFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        User user = (User)request.getSession().getAttribute("session_user");
        User admin = (User)request.getSession().getAttribute("session_admin");
        if(user != null || admin != null){
            chain.doFilter(req, resp);
        }else{
            request.setAttribute("msg","你还没有登录！");
            request.getRequestDispatcher("/jsps/user/login.jsp").forward(request,response);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
