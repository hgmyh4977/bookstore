package cn.myh.bookstore.book.web.servlet.admin;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.myh.bookstore.book.domain.Book;
import cn.myh.bookstore.book.service.BookException;
import cn.myh.bookstore.book.service.BookService;
import cn.myh.bookstore.category.domain.Category;
import cn.myh.bookstore.category.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * description: ${NAME} <br>
 * date: 2020/8/7 19:50 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
@WebServlet(name = "/admin/AdminBookServlet",urlPatterns = "/admin/AdminBookServlet")
public class AdminBookServlet extends BaseServlet {
    private BookService bookService = new BookService();
    private CategoryService categoryService = new CategoryService();
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> bookList = bookService.findAll();
        request.setAttribute("bookList",bookList);
        return "f:/adminjsps/admin/book/list.jsp";
    }
    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        Book book = bookService.load(bid);
        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("book",book);
        request.setAttribute("categoryList",categoryList);
        return "f:/adminjsps/admin/book/desc.jsp";
    }
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        bookService.deleteByBid(bid);
        return findAll(request,response);
    }
    public String modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        Category category = categoryService.getCategory(cid);
        Book book = CommonUtils.toBean(request.getParameterMap(),Book.class);
        String price = request.getParameter("price");
        price = price.substring(0,price.length()-1);
        book.setPrice(Integer.parseInt(price));
        book.setCategory(category);
        try {
            bookService.modify(book);
        } catch (BookException e) {
            request.setAttribute("msg",e.getMessage());
            return "f:/adminjsps/admin/msg.jsp";
        }
        return findAll(request,response);
    }
    public String addPre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
         * 查询所有分类，保存到request，转发到add.jsp
         * add.jsp中把所有的分类使用下拉列表显示在表单中
         */
        request.setAttribute("categoryList", categoryService.findAll());
        return "f:/adminjsps/admin/book/add.jsp";
    }
}
