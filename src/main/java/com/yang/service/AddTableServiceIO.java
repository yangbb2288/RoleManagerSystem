package com.yang.service;

import com.yang.entity.Permission;
import com.yang.entity.Role;
import com.yang.entity.result.Result;

public interface AddTableServiceIO {
    boolean addTable(Permission permission, Role role);
    boolean deleteTable(Permission permission,Role role);
}
