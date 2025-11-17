package com.yang.controller;

import com.yang.entity.Role;
import com.yang.entity.User;
import com.yang.entity.result.Result;
import com.yang.service.RoleServiceIO;
import com.yang.service.UserServiceIO;
import com.yang.service.impl.RoleService;
import com.yang.service.impl.UserService;
import org.apache.log4j.Logger;

public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    UserServiceIO userService = new UserService();
    RoleServiceIO roleService = new RoleService();

    //查询所有用户
    public Result queryAllUser() {
        logger.info("查询所有用户:");
        try {
            User[] users = userService.selectAllUser();
            if (users != null) {
                logger.info("查询所有用户成功");
                logger.info("查询所有用户信息:" + users);
                return new Result(200, "查询成功", users);
            }
            logger.info("查询所有用户失败");
            return new Result(400, "查询失败", null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(500, e.getMessage(), null);
        }
    }

    //查询 用户
    public Result queryUser(User user) {
        logger.info("查询用户:");
        try {
            User user1 = userService.selectUser(user);
            if (user1 != null) {
                logger.info("查询用户成功");
                logger.info("查询用户信息:" + user);
                return new Result(200, "查询成功", user1);
            }
            logger.info("查询用户失败");
            return new Result(400, "查询失败", null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(500, e.getMessage(), null);
        }
    }

    //修改 用户
    public Result updateUser(User user) {
        logger.info("修改用户:");
        try {
            logger.info("修改用户信息:" + user);
            boolean flag = userService.update(user);
            if (flag) {
                logger.info("修改用户成功");
                return new Result(200, "修改成功", null);
            }
            logger.info("修改用户失败");
            return new Result(400, "修改失败", null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(500, e.getMessage(), null);
        }
    }

    //删除 用户
    public Result deleteUser(User user) {
        logger.info("删除用户:");
        try {
            boolean flag = userService.delete(user);
            if (flag) {
                logger.info("删除用户成功");
                return new Result(200, "删除成功", null);
            }
            logger.info("删除用户失败");
            return new Result(400, "删除失败", null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(500, e.getMessage(), null);
        }
    }
}
