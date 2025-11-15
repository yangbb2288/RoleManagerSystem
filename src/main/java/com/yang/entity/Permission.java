package com.yang.entity;

public class Permission {
    private int permission_id;
    private String permissionName;
    private int role_id;

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

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permission_id=" + permission_id +
                ", permissionName='" + permissionName + '\'' +
                ", role_id='" + role_id + '\'' +
                '}';
    }
}
