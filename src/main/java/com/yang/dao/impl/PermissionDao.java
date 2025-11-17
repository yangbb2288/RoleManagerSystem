package com.yang.dao.impl;

import com.yang.dao.PermissionDaoIO;
import com.yang.entity.Permission;
import com.yang.entity.Role;
import com.yang.entity.result.Result;
import com.yang.until.JDBC;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PermissionDao implements PermissionDaoIO {
    public Permission selectPermission(Permission permission) {
        return null;
    }

    public boolean addPermission(Permission permission) {
        if(selectPermission(permission)!=null){
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

    public Permission[] selectALLPermission(){
        return null;
    }

}
