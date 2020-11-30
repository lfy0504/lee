package com.isabella.lee.result;

public enum ResultCode {
    SUCCESS(0, "请求成功"),
    ERROR(-1, "请求失败"),

    //用户
    USER_REGISTER(401,"用户注册失败"),
    USER_NAME_REPEAT(402,"用户名重复"),
    USER_AUTH_FAIL(403,"用户名或密码错误"),
    USER_LOCK_FAIL(404,"用户被锁定"),
    USER_TOKEN_ERROR(405,"TOKEN生成异常"),
    USER_LOGIN_SUCCESS(400,"登录成功"),





    //代码生成报错
    DRIVER_NOT_EXIST(101, "数据库驱动不存在"),
    SQL_NOT_EXIST(102, "数据库查询语句不存在"),
    TABLE_NOT_EXIST(103, "查询表结构结果为空"),

    SQL_SELECT_ERROR(104, "查询失败"),
    SQL_SAVE_ERROR(105, "保存失败"),
    SQL_UPDATE_ERROR(106, "更新失败"),
    SQL_UPDATE_NONE(107, "无更新数据"),
    SQL_DELETE_ERROR(108, "删除失败"),
    SQL_DELETE_NONE(109, "无删除数据"),

    //Activiti
    ACTIVITI_NOT_KEY(201,"没有找到流程KEY信息"),
    OBJECT_NOT_FOUND(202,"没有发现工作流模型"),
    ACTIVITI_NO_EXISTS(203,"流程定义不存在"),
    ACTIVITI_START_FAIL(204,"启动流程实例失败"),
    ACTIVITI_NO_TASK(205,"未查找到当前任务"),


    ;

    //生成时候报错

    private Integer code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
