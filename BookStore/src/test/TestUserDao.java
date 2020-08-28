package test;

import cn.itcast.commons.CommonUtils;
import cn.myh.bookstore.user.dao.UserDao;
import cn.myh.bookstore.user.domain.User;
import org.junit.Test;

/**
 * description: TestUserDao <br>
 * date: 2020/8/5 10:30 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class TestUserDao {
    private UserDao userDao = new UserDao();
    @Test
    public void testAdd(){
        User user = new User();
        user.setUid(CommonUtils.uuid());
        user.setUsername("mmm123");
        user.setPassword("123456");
        user.setEmail("13121244@qq.com");
        // user.setState(true);
        user.setCode(CommonUtils.uuid()+CommonUtils.uuid());
        userDao.add(user);
    }
    @Test
    public void testFindByUsername(){
        String username = "myh123";
        User user = userDao.findByUsername(username);
        System.out.println(user);
    }
    @Test
    public void testFindByEmail(){
        User user = userDao.findByEmail("123456@qq.com");
        System.out.println(user);
    }
}
