package com.yang.dao.impl;

import com.yang.dao.RoleDaoIO;
import com.yang.entity.Role;
import com.yang.entity.result.Result;
import com.yang.until.JDBC;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDao implements RoleDaoIO {
    public boolean addrole(Role role){
        Role role1 = selectrole(role);
        if(role1!=null){
            throw new RuntimeException("角色已存在");
        }
        try {
            Result result = JDBC.Jdbc().run("insert into role values('"
                    + role.getId() + "','"
                    + role.getRoleName() + "','"
                    + role.getPermission_id() + "')", 1);
            if (result.getCode() == 200) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean deleterole(Role role){
        try {
            Result result = JDBC.Jdbc().run("delete from role where role_id = '" + role.getId() + "'", 3);
            if(result.getCode() == 200) return true;
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean updaterole(Role role){
        try {
            Result result = JDBC.Jdbc().run("update role set role_name = '"
                    + role.getRoleName() +  "' where role_id = '"
                    + role.getId() + "'", 1);
            if(result.getCode() == 200) return true;
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Role selectrole(Role role){
        Result s=null;
        ResultSet rs = null;
        if(role.getRoleName()==null){
            try {
                s= JDBC.Jdbc().run("select * from role where role_id = '" + role.getId() + "'", 2);
                rs = (ResultSet) s.getData();
                if (!rs.next()) return null;
                role.setId(rs.getInt("role_id"));
                role.setRoleName(rs.getString("role_name"));
                role.setPermission_id(rs.getString("permission_id"));
                return role;
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try {
            s= JDBC.Jdbc().run("select * from role where role_name = '" + role.getRoleName() + "'", 2);
            rs = (ResultSet) s.getData();
            if (!rs.next()) return null;
            role.setId(rs.getInt("role_id"));
            role.setRoleName(rs.getString("role_name"));
            role.setPermission_id(rs.getString("permission_id"));
            return role;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public Role[] selectall(){
        try {
            Result s= JDBC.Jdbc().run("select * from role", 2);
            ResultSet rs = (ResultSet) s.getData();
            if(s.getCode()!=200){
                return null;
            }
            List<Role> roles = new ArrayList<Role>();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("role_id"));
                role.setRoleName(rs.getString("role_name"));
                role.setPermission_id(rs.getString("permission_id"));
                roles.add(role);
            }
            return roles.toArray(new Role[roles.size()]);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
