package com.yang.entity.result;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 创建一个简单的数据容器类
public class ResultSetData {
    private List<Map<String, Object>> rows = new ArrayList<>();
    private List<String> columnNames = new ArrayList<>();

    // getters and setters
    public List<Map<String, Object>> getRows() { return rows; }
    public void setRows(List<Map<String, Object>> rows) { this.rows = rows; }
    public List<String> getColumnNames() { return columnNames; }
    public void setColumnNames(List<String> columnNames) { this.columnNames = columnNames; }
}

