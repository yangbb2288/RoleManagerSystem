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
        System.out.println("\n\n\n\n==================登录界面===================");
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
            n_role.setRoleName(n_user.getRole_name());
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
        System.out.println("\n\n\n\n==================注册界面===================");
        System.out.print("请输入用户名:");
        String name = scanner.next();
        scanner.nextLine();
        System.out.print("请输入密码:");
        String password = scanner.next();
        scanner.nextLine();
        System.out.print("再次输入密码:");
        String password1 = scanner.next();
        scanner.nextLine();
        if (!password.equals(password1)) {
            System.out.println("两次密码不一致");
            waitF();
            return;
        }
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Result result = loginController.register(user);
        if (result.getCode() == 200) {
            System.out.println(result.getMsg());
            n_user = (User) result.getData();
            n_role.setRoleName(n_user.getRole_name());
            waitF();
            mainMenu.main_manager();
        } else {
            System.out.println(result.getMsg());
            waitF();
        }
    }
}
