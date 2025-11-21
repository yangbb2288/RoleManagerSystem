package com.yang.dao.impl;

import com.yang.dao.Permission_RoleDaoIO;
import com.yang.dao.RoleDaoIO;
import com.yang.entity.Permission;
import com.yang.entity.Role;
import com.yang.entity.result.Result;
import com.yang.until.JDBC;

import java.sql.ResultSet;
import java.util.*;

public class RoleDao extends JDBC implements RoleDaoIO {
    Permission_RoleDaoIO permission_RoleDaoIO = new Permission_RoleDao();

    public boolean addrole(Role role) {
        try {
            Result results = JDBC.Jdbc().run("select * from role where role_name='"
                    + role.getRoleName() + "'", 2);
            ResultSet rs = (ResultSet) results.getData();
            if (rs.next()) {
                throw new RuntimeException("角色已存在");
            }
            Result result = JDBC.Jdbc().run("insert into role values('"
                    + role.getId() + "','"
                    + role.getRoleName() + "',)'", 1);
            if (result.getCode() == 200) {
                return true;
            }
            return false;
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
            Result result = JDBC.Jdbc().run("delete from role where role_id = '" + role.getId() + "'", 3);
            if (result.getCode() == 200) return true;
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updaterole(Role role) {
        try {
            Result result = JDBC.Jdbc().run("update role set role_name = '"
                    + role.getRoleName() + "' where role_id = '"
                    + role.getId() + "'", 1);
            if (result.getCode() == 200) return true;
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Role selectrole(Role role) {
        try {
            Result s = null;
            ResultSet rs = null;
            if (role.getRoleName() == null) {
                s = JDBC.Jdbc().run("select * from role where role_id = '" + role.getId() + "'", 2);
            } else {
                s = JDBC.Jdbc().run("select * from role where role_name = '" + role.getRoleName() + "'", 2);
            }
            rs = (ResultSet) s.getData();
            if (s == null) {
                return null;
            }
            if (rs.next()) {
                return getPermission_Role(rs);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Role[] selectall() {
        try {
            Result result = JDBC.Jdbc().run("select * from role", 2);
            ResultSet rs = (ResultSet) result.getData();
            List<Role> roles = new ArrayList<>();
            while (rs.next()) {
                roles.add(getPermission_Role(rs));
            }
            return roles.toArray(new Role[roles.size()]);
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }

    public Role getPermission_Role(ResultSet rs) {
        try {
            Role role1 = new Role();
            role1.setId(rs.getInt("role_id"));
            role1.setRoleName(rs.getString("role_name"));
            int[] permission_ids = permission_RoleDaoIO.getPermission_id(role1.getId());
            if (permission_ids != null) {
                for (int permission_id : permission_ids) {
                    Result result = JDBC.Jdbc().run("select * from permission where permission_id = '" + permission_id + "'", 2);
                    ResultSet rs1 = (ResultSet) result.getData();
                    if (result.getCode() == 200 && rs1.next()) {
                        role1.getPermissions_name().add(rs1.getString("permission_name"));
                    }
                }
            }
            if (role1.getPermissions_name().size() == 0) {
                role1.getPermissions_name().add("无");
            }
            return role1;
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }
}
