package cn.myh.bookstore.order.dao;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import cn.myh.bookstore.book.domain.Book;
import cn.myh.bookstore.order.domain.Order;
import cn.myh.bookstore.order.domain.OrderItem;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * description: OrderDao <br>
 * date: 2020/8/5 19:26 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class OrderDao {
    private QueryRunner qr = new TxQueryRunner();
    public void addOrder(Order order){
        String sql = "insert into orders values(?,?,?,?,?,?)";
        Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
        Object[] params = {order.getOid(),timestamp,order.getTotal(),order.getState(),
                order.getOwner().getUid(),order.getAddress()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addOrderItem(List<OrderItem> items){
        String sql = "insert into orderitem values(?,?,?,?,?)";
        Object[][] params = new Object[items.size()][];
        OrderItem orderItem = null;
        for (int i=0;i<items.size();i++){
            orderItem = items.get(i);
            params[i] = new Object[]{orderItem.getIid(),orderItem.getCount(),orderItem.getSubtotal(),
                    orderItem.getOrder().getOid(),orderItem.getBook().getBid()};
        }
        try {
            qr.batch(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> findByUid(String uid) {
        String sql = "select * from orders where uid=?";
        // 当前用户的所有订单
        List<Order> orders = null;
        try {
            orders = qr.query(sql,new BeanListHandler<>(Order.class),uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Order order : orders) {
            this.loadOrderItems(order);
        }
        return orders;
    }

    private void loadOrderItems(Order order) {
        String sql = "select * from orderitem i,book b where i.bid = b.bid and oid=?";
        List<Map<String,Object>> mapList = null;

        try {
            mapList = qr.query(sql,new MapListHandler(),order.getOid());
            List<OrderItem> orderItems = toOrderItemList(mapList,order);
            order.setOrderItems(orderItems);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private List<OrderItem> toOrderItemList(List<Map<String,Object>> mapList,Order order){
        List<OrderItem> orderItemList = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            // Book book = new Book();
            // book.setBid((String)map.get("bid"));
            // book.setAuthor((String)map.get("author"));
            // book.setBname((String)map.get("bname"));
            // book.setImage((String)map.get("image"));
            // Number price =(Number)map.get("price");
            // book.setPrice(price.doubleValue());
            //
            // OrderItem orderItem = new OrderItem();
            // orderItem.setBook(book);
            // Number subtotal = (Number)map.get("subtotal");
            // orderItem.setSubtotal(subtotal.doubleValue());
            // orderItem.setOrder(order);
            // Number count = (Number)map.get("count");
            // orderItem.setCount(count.intValue());
            // orderItem.setIid((String)map.get("iid"));
            // orderItemList.add(orderItem);
            OrderItem orderItem = toOrderItem(map);
            orderItem.setOrder(order);
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }
    private OrderItem toOrderItem(Map<String,Object> map){
        OrderItem orderItem = CommonUtils.toBean(map,OrderItem.class);
        Book book = CommonUtils.toBean(map,Book.class);
        orderItem.setBook(book);
        return orderItem;
    }

    public int getStateByOid(String oid) {
        String sql = "select state from orders where oid=?";
        int state = 0;
        try {
            state  = (Integer)qr.query(sql,new ScalarHandler<>(),oid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return state;
    }

    public void updateState(String oid, int state) {
        String sql = "update orders set state=? where oid=?";
        Object[] params = {state,oid};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Order load(String oid) {
        String sql = "select * from orders where oid=?";
        Order order = null;
        try {
            order = qr.query(sql,new BeanHandler<Order>(Order.class),oid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadOrderItems(order);
        return order;
    }
}
