package com.yang.dao;

import com.yang.entity.Role;

public interface RoleDaoIO {
    boolean addrole(Role role);
    boolean deleterole(Role role);
    boolean updaterole(Role role);
    Role selectrole(Role role);
    Role[] selectall();
}
