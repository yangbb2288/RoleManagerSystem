package com.yang.dao;

import com.yang.entity.Permission;

public interface PermissionDaoIO {
    boolean addPermission(Permission permission);
    Permission selectPermission(Permission permission);
    Permission[] selectALLPermission();
}
