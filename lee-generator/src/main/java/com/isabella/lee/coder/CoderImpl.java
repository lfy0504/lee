package com.isabella.lee.coder;

import com.isabella.lee.coder.pojo.MainClass;
import com.isabella.lee.coder.pojo.Property;
import com.isabella.lee.config.AutoCodeConfig;
import com.isabella.lee.util.CoderUtil;
import com.isabella.lee.velocity.InitVelocity;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CoderImpl implements CoderInterface {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    //private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    //private final String USER_NAME = "root";
    //private final String PASSWORD = "lfy1756@163.com";
    //private final String URL = "jdbc:mysql://47.105.60.236:3306/" + AutoCodeConfig.DATABASE + "?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";

    public MainClass getMainClass(String tableName, String classPackage, String modelName) {
        MainClass mainClass = new MainClass();
        String lowerCase = CoderUtil.lineToHump(tableName);
        String className = CoderUtil.upperFirst(lowerCase);

        mainClass.setTableName(tableName);
        mainClass.setClassName(className);
        mainClass.setLowerCase(lowerCase);
        mainClass.setBasePackage(AutoCodeConfig.BASE_PACKAGE);
        mainClass.setModelName(modelName);
        mainClass.setClassPackage(classPackage);

        try {
            Class.forName(AutoCodeConfig.JDBC_DRIVER);
            connection = DriverManager.getConnection(AutoCodeConfig.URL, AutoCodeConfig.USER_NAME, AutoCodeConfig.PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("数据库建立成功");
            }
            statement = connection.createStatement();
            String SQL = "SELECT  `column_name`,column_comment,data_type,column_key " +
                    " FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = '" + AutoCodeConfig.DATABASE + "' " +
                    " AND TABLE_NAME = '" + tableName + "'" +
                    " order by COLUMN_KEY desc";
            resultSet = statement.executeQuery(SQL);
            List<Property> properties = new ArrayList<Property>();
            while (resultSet.next()) {
                Property property = new Property();
                String columnName = resultSet.getString("column_name");
                String columnComment = resultSet.getString("column_comment");
                String databaseType = resultSet.getString("data_type");
                String columnKey = resultSet.getString("column_key");

                property.setColumnName(columnName);
                property.setRemark(columnComment);
                property.setKey(columnKey);
                property.setDatabaseType(databaseType);

                property.setPropertyName(CoderUtil.lineToHump(columnName));
                property.setJavaType(CoderUtil.jdbcToJava(databaseType));
                property.setGetterAndSetter(CoderUtil.upperFirst(property.getPropertyName()));

                properties.add(property);
            }

            mainClass.setProperties(properties);

            statement.close();
            resultSet.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mainClass;
    }


    /**
     * 生成pojo文件
     */
    @Override
    public void createPojo(MainClass mainClass) {
        try {
            VelocityEngine velocityEngine = InitVelocity.init().getVelocityEngine();
            VelocityContext vc = new VelocityContext();
            List<Property> properties = mainClass.getProperties();
            String className = mainClass.getClassName();
            vc.put("className", className);
            vc.put("properties", properties);
            vc.put("basePackage", AutoCodeConfig.BASE_PACKAGE);
            vc.put("entityPackage", mainClass.getClassPackage());
            Template template = velocityEngine.getTemplate("/templates/pojo/pojo.vm");
            String pojoPath = AutoCodeConfig.PROJECT_PATH + mainClass.getModelName() + AutoCodeConfig.PROJECT_FOLDER + mainClass.getClassPackage() + "\\pojo";
            new File(pojoPath).mkdirs();
            PrintWriter writer = new PrintWriter(pojoPath + "\\" + className + ".java");
            template.merge(vc, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createService(MainClass mainClass) {

        try {
            VelocityEngine velocityEngine = InitVelocity.init().getVelocityEngine();
            VelocityContext vc = new VelocityContext();
            vc.put("className", mainClass.getClassName());
            vc.put("entity", mainClass.getLowerCase());
            vc.put("basePackage", AutoCodeConfig.BASE_PACKAGE);
            vc.put("entityPackage", mainClass.getClassPackage());
            Template template = velocityEngine.getTemplate("/templates/service/service.vm");
            String servicePath = AutoCodeConfig.PROJECT_PATH + mainClass.getModelName() + AutoCodeConfig.PROJECT_FOLDER + mainClass.getClassPackage() + "\\service";
            new File(servicePath).mkdirs();
            PrintWriter writer = new PrintWriter(servicePath + "\\" + mainClass.getClassName() + "Service.java");
            template.merge(vc, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createServiceImpl(MainClass mainClass) {
        try {
            VelocityEngine velocityEngine = InitVelocity.init().getVelocityEngine();
            VelocityContext vc = new VelocityContext();
            vc.put("className", mainClass.getClassName());
            vc.put("entity", mainClass.getLowerCase());
            vc.put("basePackage", AutoCodeConfig.BASE_PACKAGE);
            vc.put("entityPackage", mainClass.getClassPackage());
            Template template = velocityEngine.getTemplate("/templates/service/impl/impl.vm");
            String implPath = AutoCodeConfig.PROJECT_PATH + mainClass.getModelName() + AutoCodeConfig.PROJECT_FOLDER + mainClass.getClassPackage() + "\\service\\impl";
            new File(implPath).mkdirs();
            PrintWriter writer = new PrintWriter(implPath + "\\" + mainClass.getClassName() + "ServiceImpl.java");
            template.merge(vc, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void createMapper(MainClass mainClass) {
        try {
            VelocityEngine velocityEngine = InitVelocity.init().getVelocityEngine();
            VelocityContext vc = new VelocityContext();
            vc.put("className", mainClass.getClassName());
            vc.put("entity", mainClass.getLowerCase());
            vc.put("basePackage", AutoCodeConfig.BASE_PACKAGE);
            vc.put("entityPackage", mainClass.getClassPackage());
            Template template = velocityEngine.getTemplate("/templates/mapper/mapper.vm");
            String mapperPath = AutoCodeConfig.PROJECT_PATH + mainClass.getModelName() + AutoCodeConfig.PROJECT_FOLDER + mainClass.getClassPackage() + "\\mapper";
            new File(mapperPath).mkdirs();
            PrintWriter writer = new PrintWriter(mapperPath + "\\" + mainClass.getClassName() + "Mapper.java");
            template.merge(vc, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void createMapping(MainClass mainClass) {
        try {
            // 初始化模板引擎
            VelocityEngine velocityEngine = InitVelocity.init().getVelocityEngine();
            VelocityContext vc = new VelocityContext();
            List<Property> properties = mainClass.getProperties();
            vc.put("properties", properties);
            vc.put("tableName", mainClass.getTableName());
            vc.put("basePackage", AutoCodeConfig.BASE_PACKAGE);
            vc.put("entityPackage", mainClass.getClassPackage());
            vc.put("className", mainClass.getClassName());
            vc.put("entity", mainClass.getLowerCase());
            Template template = velocityEngine.getTemplate("/templates/mapping/mapping.vm");
            String mapperPath = AutoCodeConfig.PROJECT_PATH + mainClass.getModelName() + AutoCodeConfig.MAPPING_PATH + mainClass.getClassPackage();
            new File(mapperPath).mkdirs();
            PrintWriter writer = new PrintWriter(mapperPath + "\\" + mainClass.getClassName() + "Mapping.xml");
            template.merge(vc, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createController(MainClass mainClass) {
        try {
            VelocityEngine ve = InitVelocity.init().getVelocityEngine();
            VelocityContext vc = new VelocityContext();
            vc.put("className", mainClass.getClassName());
            vc.put("entity", mainClass.getLowerCase());
            vc.put("basePackage", mainClass.getBasePackage());
            vc.put("entityPackage", mainClass.getClassPackage());
            Template template = ve.getTemplate("/templates/controller/controller.vm");
            String controllerPath = AutoCodeConfig.PROJECT_PATH + AutoCodeConfig.WEB_MODEL + AutoCodeConfig.CONTROLLER_PATH + mainClass.getClassPackage();
            new File(controllerPath).mkdirs();
            PrintWriter writer = new PrintWriter(controllerPath + "\\" + mainClass.getClassName() + "Controller.java");
            template.merge(vc, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
