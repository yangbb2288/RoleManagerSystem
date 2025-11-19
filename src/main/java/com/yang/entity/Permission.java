package com.yang.entity;

import java.util.ArrayList;
import java.util.List;

public class Permission {
    private int permission_id;
    private String permissionName;
    private List<String> roles_name=new ArrayList<>();

    public List<String> getRoles_name() {
        return roles_name;
    }

    public void setRoles_name(List<String> roles_name) {
        this.roles_name = roles_name;
    }

    public int getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(int permission_id) {
        this.permission_id = permission_id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }


    @Override
    public String toString() {
        return "Permission{" +
                "permission_id=" + permission_id +
                ", permissionName='" + permissionName + '\'' +
                '}';
    }
}
