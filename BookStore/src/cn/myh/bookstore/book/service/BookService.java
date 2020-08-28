package cn.myh.bookstore.book.service;

import cn.myh.bookstore.book.dao.BookDao;
import cn.myh.bookstore.book.domain.Book;

import java.util.List;

/**
 * description: BookService <br>
 * date: 2020/8/5 14:47 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class BookService {
    private BookDao bookDao = new BookDao();

    public List<Book> findAll() {
        return bookDao.findAll();
    }

    public List<Book> findByCategory(String cid) {
        return bookDao.findByCategory(cid);
    }

    public Book load(String bid) {
        return bookDao.findByBid(bid);
    }

    public void deleteByBid(String bid) {
        bookDao.deleteByBid(bid);
    }

    public void modify(Book book) throws BookException {
        List<Book> books = findAll();
        for (Book book1 : books) {
            if(!book1.getBid().equals(book.getBid())){
                if(book1.getBname().equals(book.getBname())) {
                    throw new BookException("书名与书库中某本书重合，不能修改！");
                }
            }
        }
        bookDao.modify(book);
    }

    public void add(Book book) {
        bookDao.add(book);
    }
}
