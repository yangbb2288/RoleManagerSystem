package com.yang.dao;

public interface Permission_RoleDaoIO {
    boolean addPermission_Role(int permission_id,int role_id);
    boolean deletePermission_Role(int permission_id,int role_id);
    int[] getPermission_id(int role_id);
    int[] getRole_id(int permission_id);
}
