package com.yang.controller;

import com.yang.entity.User;
import com.yang.entity.result.Result;
import com.yang.service.UserServiceIO;
import com.yang.service.impl.UserService;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class LoginController {
    private static final Logger logger = Logger.getLogger(LoginController.class);
    UserServiceIO UserServiceIO = new UserService();
    static Scanner scanner = new Scanner(System.in);

    //用户登录
    public Result login(User user) {
        logger.info("用户登录:");
        try {
            logger.info("用户信息:" + user);
            Result result = UserServiceIO.login(user);
            if (result.getCode() == 200) {
                logger.info("用户登录成功");
                return new Result(200, "登录成功", result.getData());
            } else {
                logger.info("用户登录失败");
                return new Result(400, "登录失败", null);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(500, e.getMessage(), null);
        }
    }

    //用户注册
    public Result register(User user) {
        logger.info("用户注册:");
        try {
            logger.info("用户信息:" + user);
            Result flag = UserServiceIO.register(user);
            if (flag.getCode() == 200) {
                logger.info("用户注册成功");
                return new Result(200, "注册成功", flag.getData());
            }
            logger.info("用户注册失败");
            return new Result(400, "注册失败", null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(500, e.getMessage(), null);
        }
    }
}
