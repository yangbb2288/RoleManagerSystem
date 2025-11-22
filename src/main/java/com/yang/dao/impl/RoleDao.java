package com.yang.dao.impl;

import com.yang.dao.Permission_RoleDaoIO;
import com.yang.dao.RoleDaoIO;
import com.yang.entity.Permission;
import com.yang.entity.Role;
import com.yang.entity.result.Result;
import com.yang.entity.result.ResultSetData;
import com.yang.until.JDBC;

import java.sql.ResultSet;
import java.util.*;

public class RoleDao implements RoleDaoIO {
    Permission_RoleDaoIO permission_RoleDaoIO = new Permission_RoleDao();
    JDBC jdbc = JDBC.Jdbc();

    public boolean addrole(Role role) {
        try {
            Result results = jdbc.run("select * from role where role_name='"
                    + role.getRoleName() + "'", 2);
            if(results.getCode() == 200){
                throw new RuntimeException("角色已存在");
            }
            Result result = jdbc.run("insert into role values('"
                    + role.getId() + "','"
                    + role.getRoleName() + "')", 1);
            return result.getCode() == 200;
        } catch (Exception e) {
            String message = e.toString();
            if (message.contains(":")) {
                message = message.substring(message.indexOf(":") + 2);
            }
            throw new RuntimeException(message);
        }
    }

    public boolean deleterole(Role role) {
        try {
            Result result = jdbc.run("delete from role where role_id = '" + role.getId() + "'", 3);
            return result.getCode() == 200;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updaterole(Role role) {
        try {
            Result result = jdbc.run("update role set role_name = '"
                    + role.getRoleName() + "' where role_id = '"
                    + role.getId() + "'", 1);
            return result.getCode() == 200;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Role selectrole(Role role) {
        Result s = null;
        try {
            if (role.getRoleName() == null) {
                s = jdbc.run("select * from role where role_id = '" + role.getId() + "'", 2);
            } else {
                s = jdbc.run("select * from role where role_name = '" + role.getRoleName() + "'", 2);
            }
            if(s.getCode() == 200){
                List<Role> roles = getPermission_Role((ResultSetData) s.getData());
                return roles.get(0);
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Role[] selectall() {
        ResultSet rs = null;
        try {
            Result result = jdbc.run("select * from role", 2);
            List<Role> roles = getPermission_Role((ResultSetData) result.getData());
            return roles.toArray(new Role[roles.size()]);
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }

    public List<Role> getPermission_Role(ResultSetData resultSetData) {
        List<Role> roles=new ArrayList<>();
        try {
            List<Map<String,Object>>rows=resultSetData.getRows();
            for(Map<String,Object> row:rows){
                Role role=new Role();
                role.setId((Integer)row.get("role_id"));
                role.setRoleName((String)row.get("role_name"));
                int[] permission_ids=permission_RoleDaoIO.getPermission_id(role.getId());
                for(int permission_id:permission_ids){
                    Result result = jdbc.run("select * from permission where permission_id = '" + permission_id + "'", 2);
                    if(result.getCode() == 200){
                        List<Map<String,Object>>rows1=((ResultSetData)result.getData()).getRows();
                        for (Map<String,Object> row1:rows1){
                            role.getPermissions_name().add((String)row1.get("permission_name"));
                        }
                    }
                }
                if(role.getPermissions_name().size() == 0){
                    role.getPermissions_name().add("无");
                }
                roles.add(role);
            }
            return roles;
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }
}
