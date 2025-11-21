package com.yang.dao.impl;

import com.yang.dao.Permission_RoleDaoIO;
import com.yang.entity.result.Result;
import com.yang.until.JDBC;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Permission_RoleDao extends JDBC implements Permission_RoleDaoIO {
    public boolean addPermission_Role(int permission_id, int role_id) {
        try {
            Result result = JDBC.Jdbc().run("insert into permission_role values('"
                    + permission_id + "','"
                    + role_id + "')", 1);
            if (result.getCode() == 200) {
                return true;
            }
            return false;
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }
    public boolean deletePermission_Role(int permission_id, int role_id) {
        try {
            Result result = JDBC.Jdbc().run("delete from permission_role where permission_id = '" + permission_id + "'" + "and role_id = '" + role_id + "'",3);
            if (result.getCode() == 200) return true;
            return false;
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }
    public int[] getPermission_id(int role_id) {
        try {
            Result result = JDBC.Jdbc().run("select permission_id from permission_role where role_id = '" + role_id + "'", 2);
            ResultSet rs = (ResultSet) result.getData();
            List<Integer>Permission_id = new ArrayList<>();
            while(rs.next()){
                Permission_id.add(rs.getInt("permission_id"));
            }
            return Permission_id.stream().mapToInt(Integer::intValue).toArray();
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }
    public int[] getRole_id(int permission_id) {
        try {
            Result result = JDBC.Jdbc().run("select role_id from permission_role where permission_id = '" + permission_id + "'", 2);
            ResultSet rs = (ResultSet) result.getData();
            List<Integer>Role_id = new ArrayList<>();
            while(rs.next()){
                Role_id.add(rs.getInt("role_id"));
            }
            return Role_id.stream().mapToInt(Integer::intValue).toArray();
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }
}
