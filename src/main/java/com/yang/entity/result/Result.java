package com.yang.entity.result;

public class Result {
    private static String msg;
    private static int code;
    private static Object data;
    public Result(int code,String msg,Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public static String getMsg() {
        return msg;
    }

    public static void setMsg(String msg) {
        Result.msg = msg;
    }

    public static int getCode() {
        return code;
    }

    public static void setCode(int code) {
        Result.code = code;
    }

    public static Object getData() {
        return data;
    }

    public static void setData(Object data) {
        Result.data = data;
    }
}
