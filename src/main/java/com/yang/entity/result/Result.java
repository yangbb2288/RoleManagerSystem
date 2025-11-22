package com.yang.entity.result;

public class Result {
    private String msg;
    private int code;
    private Object data;
    public Result(int code,String msg,Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }
    public Result(){
        msg="";
        code=500;
        data=null;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
