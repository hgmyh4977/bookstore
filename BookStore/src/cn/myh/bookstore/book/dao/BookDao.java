package cn.myh.bookstore.book.dao;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import cn.myh.bookstore.book.domain.Book;
import cn.myh.bookstore.category.domain.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * description: BookDao <br>
 * date: 2020/8/5 14:46 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class BookDao {
    private QueryRunner qr = new TxQueryRunner();

    public List<Book> findAll() {
        String sql = "select * from book where del=false";
        List<Book> books = null;
        try {
            books = qr.query(sql,new BeanListHandler<Book>(Book.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public List<Book> findByCategory(String cid) {
        String sql = "select * from book where cid=? and del=false";
        List<Book> books = null;
        try {
            books = qr.query(sql,new BeanListHandler<Book>(Book.class),cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public Book findByBid(String bid) {
        String sql = "select * from book b,category c where b.cid=c.cid and bid=? and del=false";

        try {
            Map<String,Object> map = qr.query(sql,new MapHandler(),bid);
            Book book = CommonUtils.toBean(map,Book.class);
            Category category = CommonUtils.toBean(map,Category.class);
            book.setCategory(category);
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCountByCid(String cid) {
        String sql = "select count(*) from book where cid=? and del=false";
        try {
            Number count = (Number)qr.query(sql,new ScalarHandler<>(),cid);
            return count.intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteByBid(String bid) {
        String sql = "update book set del=true where bid=?";
        try {
            qr.update(sql,bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void modify(Book book) {
        String sql = "update book set bname=?,price=?,author=?,cid=? where bid=?";
        Object[] params = {book.getBname(),book.getPrice(),
                book.getAuthor(),book.getCategory().getCid(),book.getBid()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Book book) {
        String sql = "insert into book values(?,?,?,?,?,?,?)";
        Object[] params = {book.getBid(),book.getBname(),book.getPrice(),
                book.getAuthor(),book.getImage(),book.getCategory().getCid(),false};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
