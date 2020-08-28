package test;

import cn.itcast.jdbc.TxQueryRunner;
import cn.myh.bookstore.book.domain.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * description: c3p0Test <br>
 * date: 2020/8/5 10:26 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class c3p0Test {
    private QueryRunner qr = new TxQueryRunner();
    @Test
    public void fun1(){
        String sql = "select * from book";
        List<Book> books = null;
        try {
            books = qr.query(sql,new BeanListHandler<Book>(Book.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(books);
    }
}
