package com.yang.dao.impl;

import com.yang.dao.UserDaoIO;
import com.yang.entity.User;
import com.yang.entity.result.Result;
import com.yang.until.JDBC;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserDaoIO {
    //1为修改用户信息，2为查询用户信息，3为删除用户
    public User[] selectall() {
        try {
            Result result = JDBC.Jdbc().run("select * from user", 2);
            ResultSet rs = (ResultSet) result.getData();
            if(result.getCode()!=200){
                return null;
            }
            List<User> users = new ArrayList<>();
            int i = 0;
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setRole_id(rs.getInt("role_id"));
                users.add( user);
            }
            return users.toArray(new User[users.size()]);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    //查找单个用户
    public User selectone(User user) {
        String name = user.getName();
        if(name==null){
            try {
                Result result = JDBC.Jdbc().run("select * from user where user_id = '" + user.getUser_id() + "'", 2);
                ResultSet rs = (ResultSet) result.getData();
                if (!rs.next()) return null;
                User user1 = new User();
                user1.setUser_id(rs.getInt("user_id"));
                user1.setName(rs.getString("name"));
                user1.setPassword(rs.getString("password"));
                user1.setRole_id(rs.getInt("role_id"));
                return user1;
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        try {
            Result result = JDBC.Jdbc().run("select * from user where name = '" + name + "'", 2);
            ResultSet rs = (ResultSet) result.getData();
            if (!rs.next()) return null;
            User user1 = new User();
            user1.setUser_id(rs.getInt("user_id"));
            user1.setName(rs.getString("name"));
            user1.setPassword(rs.getString("password"));
            user1.setRole_id(rs.getInt("role_id"));
            return user1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //增加用户
    public boolean adduser(User user) {
        try {
            user.setRole_id(1);
            Result result = JDBC.Jdbc().run("insert into user values('"
                    + user.getUser_id() + "','"
                    + user.getName() + "','"
                    + user.getPassword() + "','"
                    + user.getRole_id() + "')", 1);
            if (result.getCode() == 200) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //删除用户
    public boolean deleteuser(User user) {
        try {
            Result result = JDBC.Jdbc().run("delete from user where user_id = '" + user.getUser_id() + "'", 3);
            if (result.getCode() == 200) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //修改用户信息
    public boolean updateuser(User user) {
        try {
            Result result = JDBC.Jdbc().run("update user set name = '" + user.getName() + "',password = '" + user.getPassword() + "',role_id = '" + user.getRole_id() + "' where user_id = '" + user.getUser_id() + "'", 1);
            if (result.getCode() == 200) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
