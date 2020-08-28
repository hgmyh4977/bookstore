package cn.myh.bookstore.order.domain;

import cn.myh.bookstore.book.domain.Book;

/**
 * description: OrderItem <br>
 * date: 2020/8/5 19:06 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class OrderItem {
    private String iid;
    private int count;
    private double subtotal;
    private Order order;
    private Book book;

    @Override
    public String toString() {
        return "OrderItem{" +
                "iid='" + iid + '\'' +
                ", count=" + count +
                ", subtotal=" + subtotal +
                ", order=" + order +
                ", book=" + book +
                '}';
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
