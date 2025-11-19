package com.yang.controller;

import com.yang.entity.Permission;
import com.yang.entity.result.Result;
import com.yang.service.PermissionServiceIO;
import com.yang.service.impl.PermissionService;
import org.apache.log4j.Logger;

public class PermissionController {
    PermissionServiceIO permissionServiceIO=new PermissionService();
    private static final Logger logger = Logger.getLogger(PermissionController.class);
    public Result addPermission(Permission permission){
        logger.info("添加权限:");
        try {
            logger.info("添加权限信息:"+permission);
            boolean flag = permissionServiceIO.addPermission(permission);
            if(flag){
                logger.info("添加权限成功");
                return new Result(200,"添加成功",null);
            }
            logger.info("添加权限失败");
            return new Result(400,"添加失败",null);
        }catch (Exception e){
            return new Result(500,e.getMessage(),null);
        }
    }

    public Result deletePermission(Permission permission){
        logger.info("删除权限:");
        try {
            logger.info("删除权限开始");
            boolean flag = permissionServiceIO.deletePermission(permission);
            if(flag){
                logger.info("删除权限成功");
                return new Result(200,"删除成功",null);
            }
            logger.info("删除权限失败");
            return new Result(400,"删除失败",null);
        }catch (Exception e){
            return new Result(500,e.getMessage(),null);
        }
    }

    public Result updatePermission(Permission permission){
        logger.info("修改权限:");
        try {
            logger.info("修改权限开始");
            boolean flag = permissionServiceIO.updatePermission(permission);
            if(flag){
                logger.info("修改权限成功");
                return new Result(200,"修改成功",null);
            }
            logger.info("修改权限失败");
            return new Result(400,"修改失败",null);
        }catch (Exception e){
            return new Result(500,e.getMessage(),null);
        }
    }
    public Result selectALLPermission(){
        logger.info("查询所有权限:");
        try {
            logger.info("查询所有权限开始");
            return new Result(200,"查询成功",permissionServiceIO.selectALLPermission());
        }catch (Exception e){
            return new Result(500,e.getMessage(),null);
        }
    }

    public Result selectPermission(Permission permission){
        logger.info("查询权限:");
        try {
            logger.info("查询权限开始");
            return new Result(200,"查询成功",permissionServiceIO.selectPermission(permission));
        }catch (Exception e){
            return new Result(500,e.getMessage(),null);
        }
    }
}
