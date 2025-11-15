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
}
