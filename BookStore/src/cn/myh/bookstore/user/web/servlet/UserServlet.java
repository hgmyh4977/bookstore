package cn.myh.bookstore.user.web.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.myh.bookstore.cart.domain.Cart;
import cn.myh.bookstore.user.domain.User;
import cn.myh.bookstore.user.service.UserException;
import cn.myh.bookstore.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * description: ${NAME} <br>
 * date: 2020/8/2 21:40 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
@WebServlet(name = "UserServlet",urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserService();
    public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User form = CommonUtils.toBean(request.getParameterMap(),User.class);
        form.setUid(CommonUtils.uuid());
        form.setCode(CommonUtils.uuid() + CommonUtils.uuid());
        form.setState(true);
        Map<String,String> errors = new HashMap<>();
        // 页面表单输入校验
        if(form.getUsername().trim().equals("") || form.getUsername() == null ){
            errors.put("username","用户名不能为空");
        }else if(form.getUsername().length()<5 || form.getUsername().length()>10){
            errors.put("username","用户名长度为5~10");
        }
        if(form.getPassword().trim().equals("") || form.getPassword() == null ){
            errors.put("password","密码不能为空");
        }else if (form.getPassword().length()<5 || form.getPassword().length()>10 ){
            errors.put("password","密码长度为5~10");
        }
        if(form.getEmail().trim().equals("") || form.getEmail() == null ){
            errors.put("email","邮箱不能为空");
        }else if(!form.getEmail().matches("\\w+@\\w+\\.\\w+")){
            errors.put("email","邮箱格式错误！");
        }
        if(errors.size()>0){
            request.setAttribute("errors",errors);
            request.setAttribute("form",form);
            return "f:/jsps/user/regist.jsp";
        }
        // 数据库校验
        try {
            userService.regist(form);
        } catch (UserException e) {
            request.setAttribute("msg",e.getMessage());
            request.setAttribute("form",form);
            return "f:/jsps/user/regist.jsp";
        }
        // 注册成功
        // Properties props = new Properties();
        // InputStream in = this.getClass().getClassLoader().getResourceAsStream("email_template.properties");
        // props.load(in);
        // String host = props.getProperty("host");
        // String uname = props.getProperty("uname");
        // String pwd = props.getProperty("pwd");
        // String from = props.getProperty("from");
        // String to = form.getEmail();
        // String subject = props.getProperty("subject");
        // String content = props.getProperty("content");
        // content = MessageFormat.format(content,form.getCode()); // 替换{0}占位符
        request.setAttribute("msg","注册成功！");
        return "f:/jsps/msg.jsp";
    }
    public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        try {
            userService.active(code);
            request.setAttribute("msg","激活成功");
        } catch (UserException e) {
            request.setAttribute("msg",e.getMessage());
            return "f:/jsps/msg.jsp";
        }
        return "f:/jsps/msg.jsp";
    }
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User form = CommonUtils.toBean(request.getParameterMap(), User.class);
        Map<String,String> errors = new HashMap<>();
        // 输入校验
        if (form.getUsername() == null || form.getUsername().trim().isEmpty()){
            errors.put("username","用户名不能为空！");
        }else if(form.getUsername().length()<5 || form.getUsername().length()>10){
            errors.put("username","用户名长度为5~10！");
        }
        if (form.getPassword() == null || form.getPassword().trim().isEmpty()){
            errors.put("password","密码不能为空！");
        }else if(form.getUsername().length()<5 || form.getUsername().length()>10){
            errors.put("password","密码长度为5~10！");
        }
        if(errors.size()>0){
            request.setAttribute("errors",errors);
            request.setAttribute("form",form);
            return "f:/jsps/user/login.jsp";
        }
        User user = null;
        try {
            user = userService.login(form);
        } catch (UserException e) {
            request.setAttribute("msg",e.getMessage());
            return "f:/jsps/user/login.jsp";
        }
        Cart cart = new Cart();
        request.getSession().setAttribute("session_user",user);
        request.getSession().setAttribute("cart",cart);
        return "r:/index.jsp";
    }
    public String quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        return "r:/index.jsp";
    }
}
