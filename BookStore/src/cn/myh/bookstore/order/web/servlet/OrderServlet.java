package cn.myh.bookstore.order.web.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.myh.bookstore.cart.domain.Cart;
import cn.myh.bookstore.cart.domain.CartItem;
import cn.myh.bookstore.order.domain.Order;
import cn.myh.bookstore.order.domain.OrderItem;
import cn.myh.bookstore.order.service.OrderException;
import cn.myh.bookstore.order.service.OrderService;
import cn.myh.bookstore.user.domain.User;
import cn.myh.bookstore.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * description: ${NAME} <br>
 * date: 2020/8/5 19:26 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
@WebServlet(name = "OrderServlet",urlPatterns = "/OrderServlet")
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderService();
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        Collection<CartItem> cartItems = cart.getCartItems();
        Order order = new Order();
        order.setOid(CommonUtils.uuid());
        System.out.println(order.getOid());
        order.setOrdertime(new Date());
        // order.setAddress("广东省肇庆市端州区"); 地址先不处理，等下单后填写地址再修改！
        order.setOwner((User)request.getSession().getAttribute("session_user"));
        order.setState(1); //刚创建的订单状态为未付款，状态为1.
        order.setTotal(cart.getTotal());

        List<OrderItem> orderItems = new ArrayList<>();

        for(CartItem cartItem:cartItems){
            OrderItem orderItem = new OrderItem();
            orderItem.setIid(CommonUtils.uuid());
            orderItem.setBook(cartItem.getBook());
            orderItem.setCount(cartItem.getCount());
            orderItem.setOrder(order);
            orderItem.setSubtotal(cartItem.getSubTotal());
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);

        // 生成订单后清空购物车
        cart.clear();
        orderService.add(order);

        request.setAttribute("order",order);
        return "f:/jsps/order/desc.jsp";
    }

    public String myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("session_user");
        List<Order> orderList = orderService.myOrders(user.getUid());
        request.setAttribute("orderList",orderList);
        return "f:/jsps/order/list.jsp";
    }
    public String confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String oid = request.getParameter("oid");
        try {
            orderService.confirm(oid);
        } catch (OrderException e) {
            request.setAttribute("msg",e.getMessage());
            return "f:/jsps/msg.jsp";
        }
        request.setAttribute("msg","确认收货成功！");
        return "f:/jsps/msg.jsp";
    }
    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        Order order = orderService.myOrder(oid);
        request.setAttribute("order",order);
        return "f:/jsps/order/desc.jsp";
    }
}
