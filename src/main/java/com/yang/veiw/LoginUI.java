package com.yang.veiw;

import com.yang.MainMenu;
import com.yang.controller.LoginController;
import com.yang.entity.User;
import com.yang.entity.result.Result;

import java.util.Scanner;

public class LoginUI extends MainMenu {
    LoginController loginController = new LoginController();
    //登录页面
    public void login() {
        System.out.print("请输入用户名:");
        String name = scanner.next();
        scanner.nextLine();
        System.out.print("请输入密码:");
        String password = scanner.next();
        scanner.nextLine();
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Result result = loginController.login(user);
        if (result.getCode() == 200) {
            System.out.println(result.getMsg());
            n_user = (User) result.getData();
            waitF();
            mainMenu.main_manager();
            return;
        } else {
            System.out.println(result.getMsg());
            waitF();
        }
    }

    //注册页面
    public void register() {
        System.out.print("请输入用户名:");
        String name = scanner.next();
        scanner.nextLine();
        System.out.print("请输入密码:");
        String password = scanner.next();
        scanner.nextLine();
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Result result = loginController.register(user);
        if (result.getCode() == 200) {
            System.out.println(result.getMsg());
            n_user = (User) result.getData();
            waitF();
            mainMenu.main_manager();
        } else {
            System.out.println(result.getMsg());
            waitF();
        }
    }
}
