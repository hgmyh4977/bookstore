package cn.myh.bookstore.book.web.servlet;

import cn.itcast.servlet.BaseServlet;
import cn.myh.bookstore.book.domain.Book;
import cn.myh.bookstore.book.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * description: ${NAME} <br>
 * date: 2020/8/5 14:47 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
@WebServlet(name = "BookServlet",urlPatterns = "/BookServlet")
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookService();
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> bookList = bookService.findAll();
        request.setAttribute("bookList",bookList);
        return "f:/jsps/book/list.jsp";
    }
    public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        List<Book> bookList = bookService.findByCategory(cid);
        request.setAttribute("bookList",bookList);
        return "f:/jsps/book/list.jsp";
    }
    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        Book book = bookService.load(bid);
        request.setAttribute("book",book);
        return "f:/jsps/book/desc.jsp";
    }
}
