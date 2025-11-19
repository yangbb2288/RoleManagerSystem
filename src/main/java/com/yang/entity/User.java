package com.yang.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int user_id;
    private String name;
    private String password;
    private int role_id;
    private String role_name;
    private List<String> permissions_name=new ArrayList<>();

    public List<String> getPermissions_name() {
        return permissions_name;
    }

    public void setPermissions_name(List<String> permissions_name) {
        this.permissions_name = permissions_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role_id=" + role_id +
                '}';
    }
}
