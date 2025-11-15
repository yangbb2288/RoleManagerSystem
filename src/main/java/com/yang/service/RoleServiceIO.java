package com.yang.service;

import com.yang.entity.Role;

public interface RoleServiceIO {
    boolean addRole(Role role);
    boolean deleteRole(Role role);
    boolean updateRole(Role role);
    Role selectRole(Role role);
    Role[] selectAllRole();
}
