package com.isabella.lee.coder;

import com.isabella.lee.coder.pojo.MainClass;

public interface CoderInterface {

    MainClass getMainClass(String tableName, String classPackage, String modelName);

    /**
     * 生成pojo类
     */
    void createPojo(MainClass mainClass);

    /**
     * 生成service接口
     */
    void createService(MainClass mainClass);

    /**
     * 生成service 接口实现类
     */
    void createServiceImpl(MainClass mainClass);

    /**
     * 生成Dao 接口
     */
    void createMapper(MainClass mainClass);

    /**
     * 生成mybatis mapping文件
     */
    void createMapping(MainClass mainClass);

    /**生成controller 文件*/
    void createController(MainClass mainClass);
}
