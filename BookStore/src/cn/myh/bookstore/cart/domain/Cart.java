package cn.myh.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.*;

/**
 * description: Cart <br>
 * date: 2020/8/5 15:50 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class Cart {
    private Map<String,CartItem> map = new LinkedHashMap<>();
    public double getTotal(){
        BigDecimal sum = new BigDecimal("0");
        for (CartItem cartItem : map.values()) {
            sum = sum.add(BigDecimal.valueOf(cartItem.getSubTotal()));
        }
        return sum.doubleValue();
    }
    public void add(CartItem cartItem){
        if(map.containsKey(cartItem.getBook().getBid())) {
            CartItem _cartItem = map.get(cartItem.getBook().getBid());
            int count = _cartItem.getCount()+cartItem.getCount();
            _cartItem.setCount(count);
            map.put(cartItem.getBook().getBid(), _cartItem);
        }else{
            map.put(cartItem.getBook().getBid(),cartItem);
        }
    }
    public void clear(){
        map.clear();
    }
    public void delect(String bid){
        if(map.containsKey(bid)){
            map.remove(bid);
        }
    }
    public Collection<CartItem> getCartItems(){
        return map.values();
    }
}
