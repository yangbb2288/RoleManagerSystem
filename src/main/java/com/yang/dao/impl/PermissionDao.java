package com.yang.dao.impl;

import com.yang.dao.PermissionDaoIO;
import com.yang.entity.Permission;
import com.yang.entity.result.Result;
import com.yang.until.JDBC;

import java.sql.ResultSet;

public class PermissionDao implements PermissionDaoIO {
    public Permission selectPermission(Permission permission) {
        try {
            Result result = JDBC.Jdbc().run("select * from permission where permission_name= '" + permission.getPermissionName() + "'", 2);
            ResultSet rs = (ResultSet) result.getData();
            if (!rs.next()) return null;
            Permission permission1 = new Permission();
            permission1.setPermission_id(rs.getInt("permission_id"));
            permission1.setPermissionName(rs.getString("permission_name"));
            permission1.setRole_id(rs.getInt("role_id"));
            return permission1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addPermission(Permission permission) {
        if(selectPermission( permission)!=null){
            throw new RuntimeException("权限已存在");
        }
        try {
            Result result = JDBC.Jdbc().run("insert into permission values('"
                    + permission.getPermission_id() + "','"
                    + permission.getPermissionName() + "','"
                    + permission.getRole_id() + "')", 1);
            if (result.getCode() == 200) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
