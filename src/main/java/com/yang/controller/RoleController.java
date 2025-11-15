package com.yang.controller;

import com.yang.entity.Role;
import com.yang.entity.result.Result;
import com.yang.service.impl.RoleService;
import org.apache.log4j.Logger;

public class RoleController {
    private static final Logger logger = Logger.getLogger(RoleController.class);
    RoleService roleService=new RoleService();
    //添加角色
    public Result addRole(Role role){
        logger.info("添加角色:");
        try {
            logger.info("添加角色信息:"+role);
            boolean flag = roleService.addRole(role);
            if(flag){
                logger.info("添加角色成功");
                return new Result(200,"添加成功",null);
            }
            logger.info("添加角色失败");
            return new Result(400,"添加失败",null);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(500,e.getMessage(),null);
        }
    }
    //查询角色
    public Result queryRole(Role role){
        logger.info("查询角色:");
        try {
            Role role1 = roleService.selectRole(role);
            if(role1!=null){
                logger.info("查询角色成功");
                logger.info("查询角色信息:"+role);
                return new Result(200,"查询成功",role1);
            }
            logger.info("查询角色失败");
            return new Result(400,"查询失败",null);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(500,e.getMessage(),null);
        }
    }
    //查询所有角色
    public Result queryAllRole(){
        logger.info("查询所有角色:");
        try {
            Role[] roles = roleService.selectAllRole();
            if(roles!=null){
                logger.info("查询所有角色成功");
                logger.info("查询所有角色信息:"+roles);
                return new Result(200,"查询成功",roles);
            }
            logger.info("查询所有角色失败");
            return new Result(400,"查询失败",null);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(500,e.getMessage(),null);
        }
    }
    //修改角色
    public Result updateRole(Role role){
        logger.info("修改角色:");
        try {
            boolean flag = roleService.updateRole(role);
            if(flag){
                logger.info("修改角色成功");
                return new Result(200,"修改成功",null);
            }
            logger.info("修改角色失败");
            return new Result(400,"修改失败",null);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(500,e.getMessage(),null);
        }
    }
    //删除角色
    public Result deleteRole(Role role){
        logger.info("删除角色:");
        try {
            boolean flag = roleService.deleteRole(role);
            if(flag){
                logger.info("删除角色成功");
                return new Result(200,"删除成功",null);
            }
            logger.info("删除角色失败");
            return new Result(400,"删除失败",null);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Result(500,e.getMessage(),null);
        }
    }
}
