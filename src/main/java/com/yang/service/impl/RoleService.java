package com.yang.service.impl;

import com.yang.dao.RoleDaoIO;
import com.yang.dao.impl.RoleDao;
import com.yang.entity.Role;
import com.yang.service.RoleServiceIO;

public class RoleService implements RoleServiceIO {
    RoleDaoIO roleDao=new RoleDao();
    public boolean addRole(Role role){
        try {
            return roleDao.addrole(role);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public boolean deleteRole(Role role){
        try {
            return roleDao.deleterole(role);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public boolean updateRole(Role role){
        try {
            return roleDao.updaterole(role);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public Role selectRole(Role role){
        try {
            Role role1 = roleDao.selectrole(role);
            if(role1==null){
                throw new RuntimeException("角色不存在");
            }else return role1;
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
        }
    }

    public Role[] selectAllRole() {
        try {
            Role[] roles = roleDao.selectall();
            if(roles==null){
                throw new RuntimeException("没有角色");
            }else return roles;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
