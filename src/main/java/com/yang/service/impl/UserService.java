package com.yang.service.impl;

import com.yang.dao.UserDaoIO;
import com.yang.dao.impl.UserDao;
import com.yang.entity.User;
import com.yang.entity.result.Result;
import com.yang.service.UserServiceIO;

public class UserService implements UserServiceIO {
    UserDaoIO userDaoIO=new UserDao();
    public Result login(User user){
        if(userDaoIO.selectone(user)==null){
            throw new RuntimeException("用户不存在");
        }else if(!userDaoIO.selectone(user).getPassword().equals(user.getPassword())){
            throw new RuntimeException("密码错误");
        }
        return new Result(200,"登录成功",userDaoIO.selectone(user));
    }
    public Result register(User user){
        if(userDaoIO.selectone(user)!=null){
            throw new RuntimeException("用户已存在");
        }
        try {
            boolean flag = userDaoIO.adduser(user);
            if(flag){
                return new Result(200,"注册成功",userDaoIO.selectone(user));
            }
            return new Result(400,"注册失败",null);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    // 获取所有用户
    public User[] selectAllUser(){
        try {
            return userDaoIO.selectall();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    // 获取单个用户
    public User selectUser(User user){
       try {
           return userDaoIO.selectone(user);
       }catch (Exception e){
           throw new RuntimeException(e.getMessage());
       }
    }
    // 删除用户
    public boolean delete(User user){
        try {
            return userDaoIO.deleteuser(user);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    //修改用户
    public boolean update(User user){
        try {
            return userDaoIO.updateuser(user);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
