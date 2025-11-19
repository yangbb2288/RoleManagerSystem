package com.yang.service.impl;

import com.yang.dao.PermissionDaoIO;
import com.yang.dao.impl.PermissionDao;
import com.yang.entity.Permission;
import com.yang.service.PermissionServiceIO;

public class PermissionService implements PermissionServiceIO {
    PermissionDaoIO permissionDaoIO=new PermissionDao();
    public boolean addPermission(Permission permission){
        try {
            return permissionDaoIO.addPermission(permission);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deletePermission(Permission permission){
        try {
            return permissionDaoIO.deletePermission(permission);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updatePermission(Permission permission){
        try {
            return permissionDaoIO.updatePermission(permission);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Permission selectPermission(Permission permission){
        try {
            return permissionDaoIO.selectPermission(permission);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Permission[] selectALLPermission(){
        try {
            return permissionDaoIO.selectALLPermission();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
