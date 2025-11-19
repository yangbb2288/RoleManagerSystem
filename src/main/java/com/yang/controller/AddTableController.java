package com.yang.controller;

import com.yang.entity.Permission;
import com.yang.entity.Role;
import com.yang.entity.result.Result;
import com.yang.service.AddTableServiceIO;
import com.yang.service.impl.AddTableService;
import org.apache.log4j.Logger;

public class AddTableController {
    AddTableServiceIO addTable=new AddTableService();
    private static final Logger logger = Logger.getLogger(AddTableController.class);

    public Result addTable(Permission permission,Role role){
        logger.info("添加权限表:");
        logger.info("权限信息："+ permission+"角色信息："+role);
        try{
            if(addTable.addTable(permission,role)){
                return new Result(200,"添加成功",null);
            }
            return new Result(400,"添加失败",null);
        }catch (Exception e){
            String message = e.toString();
            if (message.contains(":")) {
                message = message.substring(message.indexOf(":") + 2);
            }
            return new Result(500,message,null);
        }
    }
    public Result deleteTable(Permission permission,Role role){
        logger.info("删除权限表:");
        logger.info("权限信息："+ permission+"角色信息："+role);
        try{
            if(addTable.deleteTable(permission,role)){
                return new Result(200,"删除成功",null);
            }
            logger.info("删除权限表失败");
            return new Result(400,"删除失败",null);
        }catch (Exception e){
            String message = e.toString();
            if (message.contains(":")) {
                message = message.substring(message.indexOf(":") + 2);
            }
            return new Result(500,message,null);
        }
    }
}
