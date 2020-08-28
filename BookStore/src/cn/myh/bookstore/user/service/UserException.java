package cn.myh.bookstore.user.service;

/**
 * description: UserException <br>
 * date: 2020/8/3 11:35 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class UserException extends Exception{
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }
}
