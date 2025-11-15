package com.yang.until;


import com.yang.entity.result.Result;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class JDBC {
    private static volatile JDBC jdbc;
    private static final String url = "jdbc:mysql:///role_system";
    private static final String user = "root";
    private static final String password = "123456";

    public static JDBC Jdbc() {
        if (jdbc == null) {
            synchronized (JDBC.class) {
                if (jdbc == null) {
                    jdbc = new JDBC();
                }
            }
        }
        return jdbc;
    }

    public Result run(String sql, int i) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet s = null;
        try {
            //注册驱动
            //Class.forName("com.mysql.jdbc.Driver");
            //建立连接
            conn = DriverManager.getConnection(url, user, password);
            //创建Statement对象
            stmt = conn.createStatement();
            //执行SQL语句
            switch (i) {
                case 1:
                    //修改数据
                    stmt.executeUpdate(sql);
                    return new Result(200, "修改成功", null);
                case 2:
                    //查询数据
                    s=stmt.executeQuery(sql);
                    return new Result(200, "查询成功", s);
                case 3:
                    //删除数据
                    stmt.executeUpdate(sql);
                    return new Result(200, "删除成功", null);
                default:
                    return new Result(500, "请输入正确的数字", null);
            }
        } catch (Exception e) {
            return new Result(500, e.getMessage(), null);
        }
    }
}
