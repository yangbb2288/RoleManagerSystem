package com.yang.veiw;

import com.yang.MainMenu;
import com.yang.controller.AddTableController;
import com.yang.controller.PermissionController;
import com.yang.controller.RoleController;
import com.yang.entity.Permission;
import com.yang.entity.Role;
import com.yang.entity.result.Result;

public class PermissionUI extends MainMenu {
    private PermissionController permissionController = new PermissionController();
    private RoleController roleController = new RoleController();
    private RoleManagerUI roleManagerUI = new RoleManagerUI();
    private AddTableController addTableController = new AddTableController();
    //权限管理
    public void permission_manager() {
        while (true) {
            System.out.println("\n==================权限管理===================");
            System.out.println("1.查询所有权限");
            System.out.println("2.增加权限");
            System.out.println("3.增加新权限");
            System.out.println("4.删除校验");
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
    public void queryAllPermission() {
        Result result = permissionController.selectALLPermission();
        System.out.println("\n==================查询列表===================");
        if (result.getCode() == 200) {
            Permission[] permissions = (Permission[]) result.getData();
            System.out.printf("|%-10s| %-15s| %-7s %n", "权限ID", "权限名", "角色");
            System.out.println("-------------------------------------------");
            for (Permission permission1 : permissions) {
                System.out.printf("|%-11d| %-14s| %-10s%n",
                        permission1.getPermission_id(),
                        permission1.getPermissionName(),
                        permission1.getRoles_name()).toString();
            }
            System.out.println("===========================================");
            waitF();
        } else {
            System.out.println(result.getMsg());
            waitF();
        }
    }

    //3.增加权限
    public void updatePermission() {
        while (true) {
            Role role = new Role();
            roleManagerUI.queryAllRole();
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
            System.out.print("删除权限（输入1），增加权限（输入2）：");
            int i = scanner.nextInt();
            scanner.nextLine();
            Permission permission = new Permission();
            queryAllPermission();
            switch (i) {
                case 1:
                    System.out.print("请输入权限Id:");
                    permission.setPermission_id(scanner.nextInt());
                    if(permissionController.selectPermission(permission)== null){
                        System.out.println("请输入正确选项");
                        waitF();
                        continue;
                    }
                    Result result1 =addTableController.deleteTable(permission, role);
                    System.out.println(result1.getMsg());
                    break;
                case 2:
                    System.out.print("请输入权限Id:");
                    permission.setPermission_id(scanner.nextInt());
                    if(permissionController.selectPermission(permission)== null){
                        System.out.println("请输入正确选项");
                        waitF();
                        continue;
                    }
                    Result result2 = addTableController.addTable(permission, role);
                    System.out.println(result2.getMsg());
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("请输入正确的选项");
                    waitF();
            }
        }
    }

    //4.添加权限
    public void addPermission() {
        Permission permission = new Permission();
        System.out.print("请输入权限名称:");
        permission.setPermissionName(scanner.next());
        scanner.nextLine();
        Result result = permissionController.addPermission(permission);
        System.out.println(result.getMsg());
        waitF();
    }

    //5.删除权限
    public void deletePermission() {
        while (true) {
            Permission permission = new Permission();
            queryAllPermission();
            System.out.print("请输入权限ID(输入0退出):");
            permission.setPermission_id(scanner.nextInt());
            scanner.nextLine();
            if (permission.getPermission_id() == 0) {
                return;
            }
            Result s = permissionController.selectPermission(permission);
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
                    Result result = permissionController.deletePermission(permission);
                    System.out.println(result.getMsg());
                    waitF();
                    break;
                case "n":
                    break;
                default:
                    System.out.println("请输入正确的选项");
                    waitF();
                    continue;
            }
        }
    }
}
