package com.yang.entity;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private int role_id;
    private String roleName;
    private List<String> permissions_name=new ArrayList<>();

    public List<String> getPermissions_name() {
        return permissions_name;
    }

    public void setPermissions_name(List<String> permissions_name) {
        this.permissions_name = permissions_name;
    }

    public int getId() {
        return role_id;
    }

    public void setId(int id) {
         this.role_id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
