package cn.myh.bookstore.order.service;

/**
 * description: OrderException <br>
 * date: 2020/8/6 22:16 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class OrderException extends Exception{
    public OrderException() {
    }

    public OrderException(String message) {
        super(message);
    }
}
