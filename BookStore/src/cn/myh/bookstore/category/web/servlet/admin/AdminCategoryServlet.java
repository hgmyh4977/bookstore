package cn.myh.bookstore.category.web.servlet.admin;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.myh.bookstore.category.domain.Category;
import cn.myh.bookstore.category.service.CategoryException;
import cn.myh.bookstore.category.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description: ${NAME} <br>
 * date: 2020/8/7 16:24 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
@WebServlet(name = "AdminCategoryServlet",urlPatterns = "/admin/AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryService();
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categoryList",categoryService.findAll());
        return "f:/adminjsps/admin/category/list.jsp";
    }
    public String addCatrgory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newCategory = request.getParameter("cname");
        try {
            categoryService.addCategory(newCategory);
        } catch (CategoryException e) {
            request.setAttribute("msg",e.getMessage());
            return "f:/adminjsps/msg.jsp";
        }
        // request.setAttribute("msg","添加分类成功！");
        return findAll(request,response);
        // return "f:/adminjsps/msg.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        try {
            categoryService.delectByCid(cid);
        } catch (CategoryException e) {
            request.setAttribute("msg",e.getMessage());
            return "f:/adminjsps/msg.jsp";
        }
        return findAll(request,response);
    }
    public String editPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        Category category = categoryService.getCategory(cid);
        request.setAttribute("category",category);
        return "f:/adminjsps/admin/category/mod.jsp";
    }
    public String modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = CommonUtils.toBean(request.getParameterMap(),Category.class);
        try {
            categoryService.modifyCategory(category);
        } catch (CategoryException e) {
            request.setAttribute("msg",e.getMessage());
            return "f:/adminjsps/msg.jsp";
        }
        return findAll(request,response);
        // return "f:/adminjsps/msg.jsp";
    }
}
