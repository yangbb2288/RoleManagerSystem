package com.yang.dao.impl;

import com.yang.dao.PermissionDaoIO;
import com.yang.dao.Permission_RoleDaoIO;
import com.yang.entity.Permission;
import com.yang.entity.result.Result;
import com.yang.until.JDBC;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PermissionDao implements PermissionDaoIO {
    Permission_RoleDaoIO permission_RoleDaoIO = new Permission_RoleDao();

    public boolean addPermission(Permission permission) {
        try {
            if (selectPermission(permission) != null) {
                throw new RuntimeException("权限已存在");
            }
            Result result = JDBC.Jdbc().run("insert into permission values('" + permission.getPermission_id() + "','" + permission.getPermissionName() + "')", 1);
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

    public boolean deletePermission(Permission permission) {
        try {
            if (selectPermission(permission) == null) {
                throw new RuntimeException("权限不存在");
            }
            Result result = JDBC.Jdbc().run("delete from permission where permission_id = '" + permission.getPermission_id() + "'", 3);
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

    public boolean updatePermission(Permission permission) {
        try {
            if (selectPermission(permission) == null) {
                throw new RuntimeException("权限不存在");
            }
            Result result = JDBC.Jdbc().run("update permission set permission_name = '" + permission.getPermissionName() + "' where permission_id = '" + permission.getPermission_id() + "'", 1);
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

    public Permission selectPermission(Permission permission) {
        try {
            Result s = null;
            ResultSet rs = null;
            if (permission.getPermissionName() == null) {
                s = JDBC.Jdbc().run("select * from permission where permission_id = '" + permission.getPermission_id() + "'", 2);
            } else {
                s = JDBC.Jdbc().run("select * from permission where permission_name = '" + permission.getPermissionName() + "'", 2);
            }
            rs = (ResultSet) s.getData();
            if (s.getCode() != 200) {
                return null;
            }
            if (rs.next()) {
                permission.setPermissionName(rs.getString("permission_name"));
                int[] role_ids = permission_RoleDaoIO.getRole_id(permission.getPermission_id());
                for (int role_id : role_ids) {
                    Result result = JDBC.Jdbc().run("select * from role where role_id = '" + role_id + "'", 2);
                    ResultSet rs1 = (ResultSet) result.getData();
                    if (result.getCode() == 200 && rs1.next()) {
                        permission.getRoles_name().add(rs1.getString("role_name"));
                    }
                }
                if (permission.getRoles_name().size() == 0) {
                    permission.getRoles_name().add("无");
                }
                return permission;
            }
            return null;
        } catch (Exception e) {
            String message = e.toString();
            if (message.contains(":")) {
                message = message.substring(message.indexOf(":") + 2);
            }
            throw new RuntimeException(message);
        }
    }

    public Permission[] selectALLPermission() {
        try {
            Result s = JDBC.Jdbc().run("select * from permission", 2);
            ResultSet rs = (ResultSet) s.getData();
            if (s.getCode() != 200) {
                return null;
            }
            List<Permission> permissions = new ArrayList<Permission>();
            while (rs.next()) {
                Permission permission = new Permission();
                permission.setPermission_id(rs.getInt("permission_id"));
                permission.setPermissionName(rs.getString("permission_name"));
                int[] role_ids = permission_RoleDaoIO.getRole_id(permission.getPermission_id());
                if (role_ids != null) {
                    for (int role_id : role_ids) {
                        Result result = JDBC.Jdbc().run("select * from role where role_id = '" + role_id + "'", 2);
                        ResultSet rs1 = (ResultSet) result.getData();
                        if (result.getCode() == 200 && rs1.next()) {
                            permission.getRoles_name().add(rs1.getString("role_name"));
                        }
                    }
                }
                if (permission.getRoles_name().size() == 0) {
                    permission.getRoles_name().add("无");
                }
                permissions.add(permission);
            }
            return permissions.toArray(new Permission[permissions.size()]);
        } catch (Exception e) {
            String message = e.toString();
            if (message.contains(":")) {
                message = message.substring(message.indexOf(":") + 2);
            }
            throw new RuntimeException(message);
        }
    }
}
