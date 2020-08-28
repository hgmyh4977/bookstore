package cn.myh.bookstore.user.dao;

import cn.itcast.jdbc.TxQueryRunner;
import cn.myh.bookstore.user.domain.User;
import cn.myh.bookstore.user.service.UserException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * description: UserDao <br>
 * date: 2020/8/2 21:39 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class UserDao {
    private QueryRunner qr = new TxQueryRunner();
    public User findByUsername(String username){
        String sql = "select * from tb_user where username=?";
        User user = null;
        try {
            user = qr.query(sql,new BeanHandler<User>(User.class),username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public User findByEmail(String email){
        String sql = "select * from tb_user where email=?";
        User user = null;
        try {
            user = qr.query(sql,new BeanHandler<User>(User.class),email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public void add(User user){
        String sql = "insert into tb_user values(?,?,?,?,?,?)";
        Object[] params = {user.getUid(),user.getUsername(),user.getPassword(),
                user.getEmail(),user.getCode(),user.isState()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findByCode(String code) {
        String sql = "select * from tb_user where code=?";
        User user = null;
        try {
            user = qr.query(sql,new BeanHandler<User>(User.class),code);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public void updateState(String uid, boolean b) {
        String sql = "update tb_user set state=? where uid=?";
        Object[] params = {b,uid};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
