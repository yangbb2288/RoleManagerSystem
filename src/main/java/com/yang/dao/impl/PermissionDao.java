package com.yang.dao.impl;

import com.yang.dao.PermissionDaoIO;
import com.yang.dao.Permission_RoleDaoIO;
import com.yang.entity.Permission;
import com.yang.entity.result.Result;
import com.yang.entity.result.ResultSetData;
import com.yang.until.JDBC;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PermissionDao implements PermissionDaoIO {
    Permission_RoleDaoIO permission_RoleDaoIO = new Permission_RoleDao();
    JDBC jdbc = JDBC.Jdbc();

    public boolean addPermission(Permission permission) {
        try {
            if (selectPermission(permission) != null) {
                throw new RuntimeException("权限已存在");
            }
            Result result = jdbc.run("insert into permission values('" + permission.getPermission_id() + "','" + permission.getPermissionName() + "')", 1);
            if (result.getCode() == 200) {
                return true;
            }
            return false;
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }

    public boolean deletePermission(Permission permission) {
        try {
            if (selectPermission(permission) == null) {
                throw new RuntimeException("权限不存在");
            }
            Result result = jdbc.run("delete from permission where permission_id = '" + permission.getPermission_id() + "'", 3);
            if (result.getCode() == 200) {
                return true;
            }
            return false;
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }

    public boolean updatePermission(Permission permission) {
        try {
            if (selectPermission(permission) == null) {
                throw new RuntimeException("权限不存在");
            }
            Result result = jdbc.run("update permission set permission_name = '" + permission.getPermissionName() + "' where permission_id = '" + permission.getPermission_id() + "'", 1);
            if (result.getCode() == 200) {
                return true;
            }
            return false;
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }

    public Permission selectPermission(Permission permission) {
        try {
            Result s = null;
            if (permission.getPermissionName() == null) {
                s = jdbc.run("select * from permission where permission_id = '" + permission.getPermission_id() + "'", 2);
            } else {
                s = jdbc.run("select * from permission where permission_name = '" + permission.getPermissionName() + "'", 2);
            }
            if (s.getCode() != 200) {
                return null;
            }
            List<Permission> permissions = getPermission_Role((ResultSetData) s.getData());
            return permissions.get(0);
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }

    public Permission[] selectALLPermission() {
        try {
            Result s = jdbc.run("select * from permission", 2);
            List<Permission> permissions = getPermission_Role((ResultSetData) s.getData());
            return permissions.toArray(new Permission[permissions.size()]);
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }

    public List<Permission> getPermission_Role(ResultSetData resultSetData) {
        try {
            List<Map<String, Object>> rows = resultSetData.getRows();
            List<Permission> permissions = new ArrayList<>();
            for (Map<String, Object> row : rows) {
                Permission permission = new Permission();
                permission.setPermission_id((Integer) row.get("permission_id"));
                permission.setPermissionName((String) row.get("permission_name"));
                int[] role_ids = permission_RoleDaoIO.getRole_id(permission.getPermission_id());
                for (int role_id : role_ids) {
                    Result result = jdbc.run("select * from role where role_id = '" + role_id + "'", 2);
                    if (result.getCode() == 200) {
                        ResultSetData roleData = (ResultSetData) result.getData();
                        List<Map<String, Object>> roleRows = roleData.getRows();
                        for (Map<String, Object> roleRow : roleRows) {
                            permission.getRoles_name().add((String) roleRow.get("role_name"));
                        }
                    }
                }
                if (permission.getRoles_name().size() == 0) {
                    permission.getRoles_name().add("无");
                }
                permissions.add(permission);
            }
            return permissions;
        } catch (Exception e) {
            String message = e.getMessage();
            throw new RuntimeException(message);
        }
    }
}
