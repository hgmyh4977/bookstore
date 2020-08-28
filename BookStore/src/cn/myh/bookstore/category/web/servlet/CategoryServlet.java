package cn.myh.bookstore.category.web.servlet;

import cn.itcast.servlet.BaseServlet;
import cn.myh.bookstore.category.domain.Category;
import cn.myh.bookstore.category.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * description: ${NAME} <br>
 * date: 2020/8/3 21:58 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
@WebServlet(name = "CategoryServlet",urlPatterns = "/CategoryServlet")
public class CategoryServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryService();
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("categoryList",categoryList);
        return "f:/jsps/left.jsp";
    }
}
