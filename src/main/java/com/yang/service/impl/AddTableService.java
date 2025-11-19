package com.yang.service.impl;

import com.yang.dao.Permission_RoleDaoIO;
import com.yang.dao.impl.Permission_RoleDao;
import com.yang.entity.Permission;
import com.yang.entity.Role;
import com.yang.service.AddTableServiceIO;

public class AddTableService implements AddTableServiceIO {
    Permission_RoleDaoIO permission_roleDaoIO=new Permission_RoleDao();
    public boolean addTable(Permission permission, Role role){
        try {
            int permission_id=permission.getPermission_id();
            int role_id=role.getId();
            return permission_roleDaoIO.addPermission_Role(permission_id,role_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean deleteTable(Permission permission, Role role){
        try {
            int permission_id=permission.getPermission_id();
            int role_id=role.getId();
            return permission_roleDaoIO.deletePermission_Role(permission_id,role_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
