package com.yang;

import com.yang.controller.PermissionController;
import com.yang.entity.Role;
import com.yang.entity.User;
import com.yang.veiw.LoginUI;
import com.yang.veiw.PermissionUI;
import com.yang.veiw.RoleManagerUI;
import com.yang.veiw.UserManagerUI;

import java.util.Scanner;

public class MainMenu {
    public static Scanner scanner = new Scanner(System.in);
    public static MainMenu mainMenu;
    public static User n_user = null;
    public static Role n_role = new Role();

    static {
        mainMenu = new MainMenu();
    }

    //初始页面
    public static void show() {
        while (true) {
            System.out.println("===========================================");
            System.out.println("               欢迎来到系统                  ");
            System.out.println("===========================================");
            System.out.println("|----------------1.登录--------------------|");
            System.out.println("|----------------2.注册--------------------|");
            System.out.println("|----------------3.退出--------------------|");
            System.out.println("===========================================");
            System.out.print("请选择:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            LoginUI loginUI = new LoginUI();
            switch (choice) {
                case 1:
                    loginUI.login();
                    break;
                case 2:
                    loginUI.register();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("请输入正确的选项");
            }
        }
    }


    //主页面
    public void main_manager() {
        while (true) {
            System.out.println("\n===================主页面===================");
            System.out.println("----------------1.用户管理------------------");
            System.out.println("----------------2.角色管理------------------");
            System.out.println("----------------3.权限管理------------------");
            System.out.println("----------------4.退出---------------------");
            System.out.println("==========================================");
            System.out.print("请选择:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            UserManagerUI userManagerUI = new UserManagerUI();
            RoleManagerUI roleManagerUI = new RoleManagerUI();
            PermissionUI permissionUI = new PermissionUI();
            switch (choice) {
                case 1:
                    userManagerUI.user_manager();
                    break;
                case 2:
                    roleManagerUI.role_manager();
                    break;
                case 3:
                    permissionUI.permission_manager();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("请输入正确的选项");
                    waitF();
            }
        }
    }


    public static void waitF() {
        System.out.print("按回车键继续...");
        scanner.nextLine();
    }
}
