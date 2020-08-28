package cn.myh.bookstore.user.service;

import cn.myh.bookstore.user.dao.UserDao;
import cn.myh.bookstore.user.domain.User;

/**
 * description: UserService <br>
 * date: 2020/8/2 21:40 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class UserService {
    private UserDao userDao = new UserDao();
    public void regist(User form) throws UserException{
        User user = userDao.findByUsername(form.getUsername());
        if(user != null){
            throw new UserException("用户名已被注册!");
        }
        user = userDao.findByEmail(form.getEmail());
        if(user != null){
            throw new UserException("用户邮箱已被注册！");
        }
        userDao.add(form);
    }

    public void active(String code) throws UserException {
        User user = userDao.findByCode(code);
        if(user == null){
            throw new UserException("用户不存在！激活码无效！");
        }
        if(user.isState()){
            throw new UserException("用户已激活，请不要重复激活！");
        }
        userDao.updateState(user.getUid(),true);
    }
    public User login(User user) throws UserException {
        User _user = userDao.findByUsername(user.getUsername());
        if(_user == null || !_user.getPassword().equals(user.getPassword())){
            throw new UserException("用户或密码错误！");
        }else if (!_user.isState()){
            throw new UserException("用户还没激活，请先去邮箱激活再登录！");
        }
        return _user;
    }
}
