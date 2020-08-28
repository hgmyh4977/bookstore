package cn.myh.bookstore.cart.web.servlet;

import cn.itcast.servlet.BaseServlet;
import cn.myh.bookstore.book.domain.Book;
import cn.myh.bookstore.book.service.BookService;
import cn.myh.bookstore.cart.domain.Cart;
import cn.myh.bookstore.cart.domain.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description: ${NAME} <br>
 * date: 2020/8/5 16:21 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
@WebServlet(name = "CartServlet",urlPatterns = "/CartServlet")
public class CartServlet extends BaseServlet {

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        String bid = request.getParameter("bid");
        int count = Integer.valueOf(request.getParameter("count"));
        Book book = new BookService().load(bid);
        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setCount(count);
        cart.add(cartItem);
        // request.getSession().setAttribute("cart",cart);
        return "f:/jsps/cart/list.jsp";
    }
    public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        cart.clear();
        // request.getSession().setAttribute("cart",cart);
        return "f:/jsps/cart/list.jsp";
    }
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        cart.delect(bid);
        // request.getSession().setAttribute("cart",cart);
        return "f:/jsps/cart/list.jsp";
    }

}
