package com.yang.veiw;

import com.yang.MainMenu;
import com.yang.controller.RoleController;
import com.yang.controller.UserController;
import com.yang.entity.User;
import com.yang.entity.result.Result;

public class UserManagerUI extends MainMenu {
    private UserController userController = new UserController();
    private RoleController roleController=new RoleController();
    private RoleManagerUI roleManagerUI = new RoleManagerUI();
    //用户管理
    public void user_manager() {
        while (true) {
            System.out.println("\n==================用户管理====================");
            System.out.println("----------------1.查询用户--------------------");
            System.out.println("----------------2.查询所有用户-----------------");
            System.out.println("----------------3.修改用户--------------------");
            System.out.println("----------------4.删除用户--------------------");
            System.out.println("----------------5.返回-----------------------");
            System.out.println("============================================");
            System.out.print("请选择:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    queryUser();
                    break;
                case 2:
                    queryAllUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("请输入正确的选项");
                    waitF();
            }
        }
    }

    //查询用户
    public void queryUser() {
        User user = new User();
        System.out.print("请输入用户名:");
        user.setName(scanner.next());
        scanner.nextLine();
        Result result = userController.queryUser(user);
        System.out.println("\n==================查询列表===================");
        if (result.getCode() == 200) {
            User user1 = (User) result.getData();
            System.out.printf("|%-10s| %-15s| %-7s |%n", "用户ID", "用户名", "角色");
            System.out.println("-------------------------------------------");
            System.out.printf("|%-11d| %-17s| %-9s|%n", user1.getUser_id(), user1.getName(), user1.getRole_name());
            System.out.println("===========================================");
            waitF();
            return;
        }
        System.out.println(result.getMsg());
        System.out.println("===========================================");
        waitF();
    }

    //查询所有用户
    public void queryAllUser() {
        Result result = userController.queryAllUser();
        System.out.println("\n==================查询列表===================");
        if (result.getCode() == 200) {
            User[] users = (User[]) result.getData();
            System.out.printf("|%-10s| %-15s| %-7s |%n", "用户ID", "用户名", "角色");
            System.out.println("-------------------------------------------");
            for (User user : users) {
                System.out.printf("|%-11d| %-17s| %-9s|%n", user.getUser_id(), user.getName(), user.getRole_name());
            }
        } else {
            System.out.println(result.getMsg());
        }
        System.out.println("===========================================");
        waitF();
    }

    //修改用户
    public void updateUser() {
        while (true) {
            User user = new User();
            user.setRole_id(-1);
            queryAllUser();
            System.out.print("请输入用户id(输入0退出):");
            user.setUser_id(scanner.nextInt());
            scanner.nextLine();
            if (user.getUser_id() == 0) {
                return;
            }
            Result result = userController.queryUser(user);
            User user1 = (User) result.getData();
            if (result.getCode() != 200) {
                System.out.println("用户不存在");
                waitF();
                continue;
            }
            System.out.print("请输入要修改属性:");
            String choice = scanner.next();
            scanner.nextLine();
            switch (choice) {
                case "用户名":
                    System.out.print("请输入新用户名:");
                    user1.setName(scanner.next());
                    break;
                case "密码":
                    System.out.print("请输入新密码:");
                    user1.setPassword(scanner.next());
                    break;
                case "角色":
                    roleManagerUI.queryAllRole();
                    System.out.print("请输入要成为的角色ID:");
                    user1.setRole_id(scanner.nextInt());
                    break;
                default:
                    System.out.println("请输入正确的选项");
                    waitF();
                    continue;
            }
            scanner.nextLine();
            Result resultd = userController.updateUser(user1);
            System.out.println(resultd.getMsg());
            waitF();
        }
    }

    //删除用户
    public void deleteUser() {
        while (true) {
            User user = new User();
            queryAllUser();
            System.out.print("请输入用户ID(输入0退出):");
            user.setUser_id(scanner.nextInt());
            scanner.nextLine();
            if (user.getUser_id() == 0) {
                return;
            }
            Result results = userController.queryUser(user);
            if (results.getCode() != 200) {
                System.out.println("用户不存在");
                waitF();
                continue;
            }
            System.out.print("确定删除吗?(y/n)");
            String choice = scanner.next();
            scanner.nextLine();
            if (choice.equals("y")) {
                Result result = userController.deleteUser(user);
                System.out.println(result.getMsg());
                waitF();
            } else if (choice.equals("n")) {
                continue;
            } else {
                System.out.println("请输入正确的选项");
                waitF();
                continue;
            }
        }
    }
}
