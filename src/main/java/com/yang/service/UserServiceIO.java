package com.yang.service;

import com.yang.entity.User;
import com.yang.entity.result.Result;


public interface UserServiceIO {
    Result login(User user);
    Result register(User user);
    boolean update(User user);
    boolean delete(User user);
    User selectUser(User user);
    User[] selectAllUser();
}
