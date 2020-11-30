package com.isabella.lee.coder.pojo;

public class Property {

    //列名
    private String columnName;
    //属性名
    private String propertyName;
    //数据库类型
    private String databaseType;
    //java类型
    private String javaType;
    //注释
    private String remark;
    //getter and setter name
    private String getterAndSetter;
    //主键
    private String key;


    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGetterAndSetter() {
        return getterAndSetter;
    }

    public void setGetterAndSetter(String getterAndSetter) {
        this.getterAndSetter = getterAndSetter;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
