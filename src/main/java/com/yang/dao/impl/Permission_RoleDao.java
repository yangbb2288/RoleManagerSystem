package com.yang.dao.impl;

import com.yang.dao.Permission_RoleDaoIO;
import com.yang.entity.result.Result;
import com.yang.entity.result.ResultSetData;
import com.yang.until.JDBC;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Permission_RoleDao implements Permission_RoleDaoIO {
    JDBC jdbc = JDBC.Jdbc();
    public boolean addPermission_Role(int permission_id, int role_id) {
        try {
            Result result = jdbc.run("insert into permission_role values('"
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
            Result result = jdbc.run("delete from permission_role where permission_id = '" + permission_id + "'" + "and role_id = '" + role_id + "'",3);
            if (result.getCode() == 200) return true;
            return false;
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }
    public int[] getPermission_id(int role_id) {
        try {
            Result result = jdbc.run("select permission_id from permission_role where role_id = '" + role_id + "'", 2);
            ResultSetData resultSetData = (ResultSetData) result.getData();
            if(result.getCode() != 200){
                return new int[0];
            }
            List<Map<String, Object>> rows = resultSetData.getRows();
            List<Integer> Permission_id = new ArrayList<>();
            for (Map<String, Object> row : rows) {
                Permission_id.add(Integer.parseInt(row.get("permission_id").toString()));
            }
            return Permission_id.stream().mapToInt(Integer::intValue).toArray();
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }
    public int[] getRole_id(int permission_id) {
        try {
            Result result = jdbc.run("select role_id from permission_role where permission_id = '" + permission_id + "'", 2);
            ResultSetData resultSetData = (ResultSetData) result.getData();
            if(result.getCode() != 200){
                return new int[0];
            }
            List<Map<String, Object>> rows = resultSetData.getRows();
            List<Integer> Role_id = new ArrayList<>();
            for (Map<String, Object> row : rows) {
                Role_id.add(Integer.parseInt(row.get("role_id").toString()));
            }
            return Role_id.stream().mapToInt(Integer::intValue).toArray();
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }
}
