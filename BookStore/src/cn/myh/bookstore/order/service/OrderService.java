package cn.myh.bookstore.order.service;

import cn.itcast.jdbc.JdbcUtils;
import cn.myh.bookstore.book.domain.Book;
import cn.myh.bookstore.order.dao.OrderDao;
import cn.myh.bookstore.order.domain.Order;
import cn.myh.bookstore.order.domain.OrderItem;
import cn.myh.bookstore.user.dao.UserDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * description: OrderService <br>
 * date: 2020/8/5 19:26 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class OrderService {
    private OrderDao orderDao = new OrderDao();
    // 添加订单 处理事务
    public void add(Order order){
        try {
            JdbcUtils.beginTransaction();
            orderDao.addOrder(order);
            orderDao.addOrderItem(order.getOrderItems());
            JdbcUtils.commitTransaction();
        } catch (SQLException e) {
            try {
                JdbcUtils.rollbackTransaction();
            } catch (SQLException ee) {
                throw new RuntimeException(ee);
            }
            throw new RuntimeException(e);
        }
    }

    public List<Order> myOrders(String uid) {
        return orderDao.findByUid(uid);
    }

    public void confirm(String oid) throws OrderException {
        int state = orderDao.getStateByOid(oid);
        if(state != 3){
            throw new OrderException("订单异常，确认收货失败！");
        }else{
            orderDao.updateState(oid,4);
        }
    }

    public Order myOrder(String oid) {
        return orderDao.load(oid);
    }
}
