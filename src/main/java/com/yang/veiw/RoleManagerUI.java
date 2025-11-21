package com.yang.veiw;

import com.yang.MainMenu;
import com.yang.controller.RoleController;
import com.yang.entity.Role;
import com.yang.entity.result.Result;

public class RoleManagerUI extends MainMenu {
    private RoleController roleController = new RoleController();
    //角色管理
    public void role_manager() {
        boolean flag = false;
        n_role =(Role) roleController.queryRole(n_role).getData();
        if(n_role == null){
            System.out.println("您没有权限");
            waitF();
            return;
        }
        for(String permission_name:n_role.getPermissions_name()){
            if(permission_name.equals("角色管理")){
                flag = true;
                break;
            }
        }
        if(!flag){
            System.out.println("您没有权限");
            waitF();
            return;
        }
        while (true) {
            System.out.println("\n==================角色管理===================");
            System.out.println("------------------1.查询角色-----------------");
            System.out.println("------------------2.显示所有角色--------------");
            System.out.println("------------------3.修改角色名称--------------");
            System.out.println("------------------4.添加角色-----------------");
            System.out.println("------------------5.删除角色-----------------");
            System.out.println("------------------6.返回--------------------");
            System.out.println("===========================================");
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
        System.out.println("\n============================查询列表===============================");
        if (result.getCode() == 200) {
            Role roles = (Role) result.getData();
            System.out.printf("|%-10s| %-15s| %-7s %n", "角色ID", "角色名", "权限");
            System.out.println("----------------------------------------------------------------");
            System.out.printf("|%-11d| %-17s| %-9s%n",
                    roles.getId(),
                    roles.getRoleName(),
                    roles.getPermissions_name().toString());
            System.out.println("================================================================");
            waitF();
            return;
        }
        System.out.println(result.getMsg());
        System.out.println("================================================================");
        waitF();
    }

    //查询所有角色
    public void queryAllRole() {
        Result result = roleController.queryAllRole();
        System.out.println("\n============================查询列表===============================");
        if (result.getCode() == 200) {
            Role[] roles = (Role[]) result.getData();
            System.out.printf("|%-10s| %-15s| %-7s %n", "角色ID", "角色名", "权限");
            System.out.println("----------------------------------------------------------------");
            for (Role role1 : roles) {
                System.out.printf("|%-11d| %-17s| %-9s%n",
                        role1.getId(),
                        role1.getRoleName(),
                        role1.getPermissions_name().toString());
            }
            System.out.println("================================================================");
            waitF();
            return;
        }
        System.out.println(result.getMsg());
        System.out.println("================================================================");
        waitF();
    }

    //修改角色
    public void updateRole() {
        while (true) {
            Role role = new Role();
            queryAllRole();
            System.out.print("请输入角色ID(输入0退出):");
            role.setId(scanner.nextInt());
            scanner.nextLine();
            if (role.getId() == 0) {
                return;
            }
            Result s = roleController.queryRole(role);
            if (s.getCode() != 200) {
                System.out.println("请输入正确选项");
                waitF();
                continue;
            }
            role = (Role) s.getData();
            System.out.print("请输入角色名:");
            role.setRoleName(scanner.next());
            scanner.nextLine();
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
            Result s = roleController.queryRole(role);
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
}
