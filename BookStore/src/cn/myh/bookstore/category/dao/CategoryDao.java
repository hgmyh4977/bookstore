package cn.myh.bookstore.category.dao;

import cn.itcast.jdbc.TxQueryRunner;
import cn.myh.bookstore.category.domain.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * description: CategoryDao <br>
 * date: 2020/8/3 21:38 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class CategoryDao {
    private QueryRunner qr = new TxQueryRunner();

    public List<Category> findAll() {
        String sql = "select * from category";
        List<Category> categoryList = null;
        try {
            categoryList = qr.query(sql,new BeanListHandler<Category>(Category.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }

    public void add(String newCategory) {
        try {
            int count = findAll().size()+1;
            String sql = "insert into category values(?,?)";
            qr.update(sql,count,newCategory);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void delectByCid(String cid) {
        String sql = "delete from category where cid=?";
        try {
            qr.update(sql,cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Category getCategoryByCid(String cid) {
        String sql = "select * from category where cid=?";
        try {
            return qr.query(sql,new BeanHandler<Category>(Category.class),cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void modifyByCid(String cid,String cname) {
        String sql = "update category set cname=? where cid=?";
        try {
            qr.update(sql,cname,cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
