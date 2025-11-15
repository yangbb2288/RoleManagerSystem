package com.yang.service;

import com.yang.entity.Permission;
import com.yang.entity.result.Result;

public interface PermissionServiceIO {
    //增加权限
    boolean addPermission(Permission permission);

    Object selectALLPermission();
}
