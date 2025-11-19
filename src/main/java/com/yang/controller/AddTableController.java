package com.yang.controller;

import com.yang.entity.Permission;
import com.yang.entity.Role;
import com.yang.entity.result.Result;
import com.yang.service.AddTableServiceIO;
import com.yang.service.impl.AddTableService;

public class AddTableController {
    AddTableServiceIO addTable=new AddTableService();

    public Result addTable(Permission permission,Role role){
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
        try{
            if(addTable.deleteTable(permission,role)){
                return new Result(200,"删除成功",null);
            }
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
