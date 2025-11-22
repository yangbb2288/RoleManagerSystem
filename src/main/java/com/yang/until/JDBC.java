package com.yang.until;


import com.yang.entity.result.Result;
import com.yang.entity.result.ResultSetData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


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
        ResultSet s = null;
        Connection conn = null;
        Statement stmt = null;
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
                    s = stmt.executeQuery(sql);
                    ResultSetData data= copyResultSet(s);
                    if(data.getRows().isEmpty()){
                        return new Result(500, "查询失败", null);
                    }
                    return new Result(200, "查询成功", data);
                case 3:
                    //删除数据
                    stmt.executeUpdate(sql);
                    return new Result(200, "删除成功", null);
                default:
                    return new Result(500, "请输入正确的数字", null);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (s != null) s.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e2) {
                throw new RuntimeException(e2.getMessage());
            }
        }
    }

    // 拷贝 ResultSet 数据的方法
    private ResultSetData copyResultSet(ResultSet rs){
        ResultSetData resultSetData = new ResultSetData();
        try {
            List<Map<String, Object>> rows = new ArrayList<>();
            int columnCount = rs.getMetaData().getColumnCount();
            List<String> columnNames = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(rs.getMetaData().getColumnName(i));
            }
            resultSetData.setColumnNames(columnNames);
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(columnNames.get(i - 1), rs.getObject(i));
                }
                rows.add(row);
            }
            resultSetData.setRows(rows);
            return resultSetData;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
