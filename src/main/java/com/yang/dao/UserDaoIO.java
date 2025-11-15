package com.yang.dao;

import com.yang.entity.User;

public interface UserDaoIO {
    User[] selectall();
    User selectone(User user);
    //增加用户
    boolean adduser(User user);
    //删除用户
    boolean deleteuser(User user);
    //修改用户信息
    boolean updateuser(User user);
}
