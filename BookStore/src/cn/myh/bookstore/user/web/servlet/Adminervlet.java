package cn.myh.bookstore.user.web.servlet;

import cn.itcast.servlet.BaseServlet;
import cn.myh.bookstore.user.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;

/**
 * description: ${NAME} <br>
 * date: 2020/8/7 23:55 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
@WebServlet(name = "Adminervlet",urlPatterns = "/AdminServlet")
public class Adminervlet extends BaseServlet {
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminname = request.getParameter("adminname");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(adminname);
        user.setPassword(password);
        if(adminname.equals("myh") && password.equals("Reehom123")){
            request.getSession().setAttribute("session_admin",user);
            return "f:/adminjsps/admin/index.jsp";
        }else {
            request.setAttribute("msg","非法访问！");
            return "f:/jsps/msg.jsp";
        }
    }
}
