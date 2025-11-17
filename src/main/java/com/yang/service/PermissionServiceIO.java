package com.yang.service;

import com.yang.entity.Permission;

public interface PermissionServiceIO {
    //增加权限
    boolean addPermission(Permission permission);

    Permission selectPermission(Permission permission);

    Permission[] selectALLPermission();
}
