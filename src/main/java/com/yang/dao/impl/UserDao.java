package com.yang.dao.impl;

import com.yang.dao.UserDaoIO;
import com.yang.entity.User;
import com.yang.entity.result.Result;
import com.yang.entity.result.ResultSetData;
import com.yang.until.JDBC;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao implements UserDaoIO {
    JDBC jdbc = JDBC.Jdbc();

    //1为修改用户信息，2为查询用户信息，3为删除用户
    public User[] selectall() {
        try {
            //select user and  role
            Result result = jdbc.run("select u.*, r.role_name " + "from user u " + "left join role r on u.role_id = r.role_id", 2);
            if (result.getCode() == 200) {
                ResultSetData data = (ResultSetData) result.getData();
                List<User> users = getUser(data);
                return users.toArray(new User[users.size()]);
            } else {
                return null;
            }
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }

    //查找单个用户
    public User selectone(User user) {
        String name = user.getName();
        Result result = null;
        if (name == null) {
            result = jdbc.run("select u.*,r.role_name from user u left join role r on r.role_id=u.role_id where user_id = '" + user.getUser_id() + "'", 2);
        } else {
            result = jdbc.run("select u.*,r.role_name from user u left join role r on r.role_id=u.role_id where name = '" + user.getName() + "'", 2);
        }
        if (result.getCode() == 200) {
            ResultSetData data = (ResultSetData) result.getData();
            List<User> users = getUser(data);
            return users.get(0);
        } else {
            return null;
        }
    }

    //增加用户
    public boolean adduser(User user) {
        try {
            user.setRole_id(-1);
            Result result = jdbc.run("insert into user values('"
                    + user.getUser_id() + "','"
                    + user.getName() + "','"
                    + user.getPassword() + "','"
                    + user.getRole_id() + "')", 1);
            return result.getCode() == 200;
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }

    //删除用户
    public boolean deleteuser(User user) {
        try {
            Result result = jdbc.run("delete from user where user_id = '" + user.getUser_id() + "'", 3);
            return result.getCode() == 200;
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }

    //修改用户信息
    public boolean updateuser(User user) {
        try {
            Result result = jdbc.run("update user set name = '" + user.getName() + "',password = '" + user.getPassword() + "',role_id = '" + user.getRole_id() + "' where user_id = '" + user.getUser_id() + "'", 1);
            return result.getCode() == 200;
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }

    public List<User> getUser(ResultSetData resultSetData) {
        List<User> users = new ArrayList<>();
        try {
            List<Map<String, Object>> data = resultSetData.getRows();
            for (Map<String, Object> map : data) {
                User user = new User();
                user.setUser_id((Integer) map.get("user_id"));
                user.setName((String) map.get("name"));
                user.setPassword((String) map.get("password"));
                user.setRole_id((Integer) map.get("role_id"));
                user.setRole_name((String) map.get("role_name"));
                if (user.getRole_name() == null) {
                    user.setRole_name("无");
                }
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
