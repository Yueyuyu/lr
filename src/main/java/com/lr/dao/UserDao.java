package com.lr.dao;

import com.lr.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
   //获得一个User类
    @Select("select * from user where username=#{username} limit 1")
    User getOneUser(String username);

    //插入一个User
    @Insert("insert into user (username,password) values(#{username},#{password})")
    boolean setOneUser(User user);

}
