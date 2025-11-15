package com.yang;

import com.yang.controller.LoginController;
import com.yang.controller.PermissionController;
import com.yang.controller.RoleController;
import com.yang.controller.UserController;
import com.yang.entity.Permission;
import com.yang.entity.Role;
import com.yang.entity.User;
import com.yang.entity.result.Result;

import java.util.Objects;
import java.util.Scanner;

public class MainMenu {
    LoginController loginController = new LoginController();
    RoleController roleController = new RoleController();
    UserController userController = new UserController();
    PermissionController permissionController = new PermissionController();
    private static Scanner scanner = new Scanner(System.in);
    private static MainMenu mainMenu;
    private static User n_user=null;

    static {
        mainMenu = new MainMenu();
    }

    //初始页面
    public static void show() {
        while (true) {
            System.out.println("欢迎来到系统");
            System.out.println("1.登录");
            System.out.println("2.注册");
            System.out.println("3.退出");
            System.out.print("请选择:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    mainMenu.login();
                    break;
                case 2:
                    mainMenu.register();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("请输入正确的选项");
            }
        }
    }

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
        System.out.println();
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

    //主页面
    public void main_manager() {
        while (true) {
            System.out.println("1.用户管理");
            System.out.println("2.角色管理");
            System.out.println("3.权限管理");
            System.out.println("4.退出");
            System.out.print("请选择:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    user_manager();
                    break;
                case 2:
                    role_manager();
                    break;
                case 3:
                    permission_manager();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("请输入正确的选项");
                    waitF();
            }
        }
    }

    //用户管理
    public void user_manager() {
        while (true) {
            System.out.println("1.查询用户");
            System.out.println("2.显示所有用户");
            System.out.println("3.修改用户");
            System.out.println("4.删除用户");
            System.out.println("5.返回");
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
        System.out.println("==================查询列表===================");
        if(result.getCode() == 200){
            User user1 = (User) result.getData();
            System.out.printf("|%-10s| %-15s| %-7s |%n", "用户ID", "用户名", "角色ID");
            System.out.println("-------------------------------------------");
            System.out.printf("|%-11d| %-17s| %-9s|%n", user1.getUser_id(), user1.getName(), user1.getRole_id());
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
        System.out.println("==================查询列表===================");
        if (result.getCode() == 200){
            User[] users = (User[]) result.getData();
            System.out.printf("|%-10s| %-15s| %-7s |%n", "用户ID", "用户名", "角色ID");
            System.out.println("-------------------------------------------");
            for (User user : users) {
                System.out.printf("|%-11d| %-17s| %-9s|%n", user.getUser_id(), user.getName(), user.getRole_id());
            }
            System.out.println("===========================================");
            waitF();
            return;
        }
        System.out.println(result.getMsg());
        System.out.println("===========================================");
        waitF();
    }
    //修改用户
    public void updateUser() {
        while(true){
            User user = new User();
            queryAllUser();
            System.out.print("请输入用户id(输入0退出):");
            user.setUser_id(scanner.nextInt());
            scanner.nextLine();
            if (user.getUser_id()==0) {
                return;
            }
            Result result = userController.queryUser(user);
            User user1 = (User) result.getData();
            if(result.getCode()!=200){
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
                    scanner.nextLine();
                    break;
                case "密码":
                    System.out.print("请输入新密码:");
                    user1.setPassword(scanner.next());
                    scanner.nextLine();
                    break;
                case "角色ID":
                    System.out.print("请输入新角色ID:");
                    user1.setRole_id(scanner.nextInt());
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("请输入正确的选项");
                    waitF();
                    continue;
            }
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
            if (user.getUser_id()==0) {
                return;
            }
            Result results = userController.queryUser(user);
            if(results.getCode()!=200){
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
            }else if (choice.equals("n")) {
                continue;
            }else {
                System.out.println("请输入正确的选项");
                waitF();
                continue;
            }
        }
    }

    //角色管理
    public void role_manager() {
        while (true) {
            System.out.println("1.查询角色");
            System.out.println("2.显示所有角色");
            System.out.println("3.修改角色");
            System.out.println("4.添加角色");
            System.out.println("5.删除角色");
            System.out.println("6.返回");
            System.out.print("请选择:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    queryRole();
                    break;
                case 2:
                    queryAllRole();
                    break;
                case 3:
                    updateRole();
                    break;
                case 4:
                    addRole();
                    break;
                case 5:
                    deleteRole();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("请输入正确的选项");
                    waitF();
            }
        }
    }

    //查询角色
    public void queryRole() {
        Role role = new Role();
        System.out.print("请输入角色名:");
        role.setRoleName(scanner.next());
        scanner.nextLine();
        Result result = roleController.queryRole(role);
        System.out.println("==================查询列表===================");
        if (result.getCode() == 200) {
            Role roles = (Role) result.getData();
            System.out.printf("|%-10s| %-15s| %-7s |%n", "角色ID", "角色名", "权限ID");
            System.out.println("-------------------------------------------");
                System.out.printf("|%-11d| %-17s| %-9s|%n",
                        roles.getId(),
                        roles.getRoleName(),
                        roles.getPermission_id());
            System.out.println("===========================================");
            waitF();
            return;
        }
        System.out.println(result.getMsg());
        System.out.println("===========================================");
        waitF();
    }

    //查询所有角色
    public void queryAllRole() {
        Result result = roleController.queryAllRole();
        System.out.println("==================查询列表===================");
        if (result.getCode() == 200) {
            Role[] roles = (Role[]) result.getData();
            System.out.printf("|%-10s| %-15s| %-7s |%n", "角色ID", "角色名", "权限ID");
            System.out.println("-------------------------------------------");
            for (Role role1 : roles) {
                System.out.printf("|%-11d| %-17s| %-9s|%n",
                        role1.getId(),
                        role1.getRoleName(),
                        role1.getPermission_id());
            }
            System.out.println("===========================================");
            waitF();
            return;
        }
        System.out.println(result.getMsg());
        System.out.println("===========================================");
        waitF();
    }

    //修改角色
    public void updateRole() {
        while ( true) {
            Role role = new Role();
            queryAllRole();
            System.out.print("请输入角色ID(输入0退出):");
            role.setId(scanner.nextInt());
            scanner.nextLine();
            if (role.getId() == 0) {
                return;
            }
            Result s=roleController.queryRole(role);
            if (s.getCode() != 200) {
                System.out.println("请输入正确选项");
                waitF();
                continue;
            }
            role = (Role) s.getData();
            System.out.print("\n请输要修改信息:");
            String info = scanner.next();
            scanner.nextLine();
            switch (info) {
                case "角色名":
                    System.out.print("请输入新角色名:");
                    role.setRoleName(scanner.next());
                    scanner.nextLine();
                    break;
                case "权限ID":
                    System.out.print("请输入新权限ID:");
                    role.setPermission_id(scanner.next());
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("请输入正确的选项");
                    waitF();
                    continue;
            }
            Result result = roleController.updateRole(role);
            System.out.println(result.getMsg());
            waitF();
        }
    }

    //添加角色
    public void addRole() {
        Role role = new Role();
        System.out.print("请输入角色名:");
        role.setRoleName(scanner.next());
        scanner.nextLine();
        System.out.print("\n请输入权限Id:");
        role.setPermission_id(scanner.next());
        scanner.nextLine();
        Result result = roleController.addRole(role);
        System.out.println(result.getMsg());
        waitF();
    }

    //删除角色
    public void deleteRole() {
        while (true) {
            Role role = new Role();
            queryAllRole();
            System.out.print("请输入角色ID(输入0退出):");
            role.setId(scanner.nextInt());
            scanner.nextLine();
            if (role.getId() == 0) {
                return;
            }
            Result s=roleController.queryRole(role);
            if (s.getCode() != 200) {
                System.out.println("请输入正确选项");
                waitF();
                continue;
            }
            System.out.print("确定删除吗?(y/n):");
            String info = scanner.next();
            scanner.nextLine();
            switch (info) {
                case "y":
                    Result result = roleController.deleteRole(role);
                    System.out.println(result.getMsg());
                    waitF();
                    break;
                case "n":
                    break;
                default:
                    System.out.println("请输入正确的选项");
                    waitF();
            }
        }
    }

    //权限管理
    public void permission_manager() {
        while (true) {
            System.out.println("==================权限管理===================");
            System.out.println("1.查询所有权限");
            System.out.println("2.修改权限");
            System.out.println("3.增加新权限");
            System.out.println("4.权限校验");
            System.out.println("5.返回");
            System.out.println("=========================================");
            System.out.print("请输入你的选择：");
            int i = scanner.nextInt();
            scanner.nextLine();
            switch (i) {
                case 1:
                    queryAllPermission();
                    break;
                case 2:
                    updatePermission();
                    break;
                case 3:
                    addPermission();
                    break;
                case 4:
                    deletePermission();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("输入正确选择");
                    break;
            }
        }
    }
    //1.查询所有权限
    public void queryAllPermission(){
        Result result=permissionController.selectALLPermission();
    }
    //3.修改权限
    public void updatePermission(){}
    //4.添加权限
    public void addPermission(){
        Permission permission = new Permission();
        System.out.print("请输入权限名称:");
        permission.setPermissionName(scanner.next());
        scanner.nextLine();
        Result result = permissionController.addPermission(permission);
        System.out.println(result.getMsg());
        waitF();
    }
    //5.删除权限
    public void deletePermission(){

    }

    public static void waitF() {
        System.out.print("按回车键继续...");
        scanner.nextLine();
    }
}
