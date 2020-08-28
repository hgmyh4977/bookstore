package cn.myh.bookstore.order.domain;

import cn.myh.bookstore.user.domain.User;

import java.util.Date;
import java.util.List;

/**
 * description: Order <br>
 * date: 2020/8/5 19:03 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class Order {
    private String oid;
    private Date ordertime;
    private double total;
    private int state;  // 四种状态: 1.未付款 2.已付款未发货 3.已发货未收货 4.确认收货，交易成功
    private User owner;
    private String address;
    private List<OrderItem> orderItems;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", ordertime=" + ordertime +
                ", total=" + total +
                ", state=" + state +
                ", owner=" + owner +
                ", address='" + address + '\'' +
                '}';
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
