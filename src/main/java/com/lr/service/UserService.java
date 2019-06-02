package com.lr.service;

import com.lr.dao.UserDao;
import com.lr.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    //自动注入一个userDao
    @Autowired
    private UserDao userDao;

    public String  register(User user) {
        //判断用户是否存在
        if (userDao.getOneUser(user.getUsername()) == null) {
            userDao.setOneUser(user);
            return "注册成功";
        }
        else {
            return "该用户名已被使用";
        }
    }
    public String login(User user) {
        //通过用户名获取用户
        User dbUser = userDao.getOneUser(user.getUsername());
        if (dbUser == null) {
            return "该用户不存在";
        }
        else if (!dbUser.getPassword().equals(user.getPassword())){
            return "密码错误";
        }
        else {
            user.setId(dbUser.getId());
            user.setCreateTime(dbUser.getCreateTime());
            return "登陆成功";
        }
    }
}
