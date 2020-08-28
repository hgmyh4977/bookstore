package cn.myh.bookstore.category.service;

import cn.myh.bookstore.book.dao.BookDao;
import cn.myh.bookstore.category.dao.CategoryDao;
import cn.myh.bookstore.category.domain.Category;

import java.util.List;

/**
 * description: CategoryService <br>
 * date: 2020/8/3 21:57 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class CategoryService {
    private CategoryDao categoryDao = new CategoryDao();
    private BookDao bookDao = new BookDao();

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    public void addCategory(String newCategory) throws CategoryException {
        List<Category> list = findAll();
        for (Category category : list) {
            if(category.getCname().equals(newCategory)) {
                throw new CategoryException("分类已存在，请不要重复添加！");
            }
        }
        categoryDao.add(newCategory);
    }

    public void delectByCid(String cid) throws CategoryException {
        // int num  = bookDao.findByCategory(cid).size(); 这种方法会出空指针异常！！！
        int num = bookDao.getCountByCid(cid);
        if(num >   0){
            throw new CategoryException("该分类下存在图书，不允许删除！");
        }
        categoryDao.delectByCid(cid);
    }

    public Category getCategory(String cid) {
        return categoryDao.getCategoryByCid(cid);
    }

    public void modifyCategory(Category c) throws CategoryException {
        List<Category> categories = findAll();
        for (Category category : categories) {
            if(category.getCname().equals((c.getCname()))){
                throw new CategoryException("与已有分类名称重复，修改失败!");
            }
        }
        categoryDao.modifyByCid(c.getCid(),c.getCname());
    }
}
