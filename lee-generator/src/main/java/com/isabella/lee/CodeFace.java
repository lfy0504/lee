package com.isabella.lee;

import com.isabella.lee.coder.CoderImpl;
import com.isabella.lee.coder.CoderInterface;
import com.isabella.lee.coder.pojo.MainClass;
import com.isabella.lee.config.AutoCodeConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CodeFace {
    public static void main(String[] args) {
        // 创建 JFrame 实例
        JFrame frame = new JFrame("Code Generate");
        // Setting the width and height of frame
        frame.setSize(350, 210);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();
        // 添加面板
        frame.add(panel);
        /*
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents(panel);

        // 设置界面可见
        frame.setVisible(true);

    }

    private static void placeComponents(JPanel panel) {


        JLabel infoLabel = new JLabel();

        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel
        JLabel tableLabel = new JLabel("表名:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        tableLabel.setBounds(10, 20, 80, 25);
        panel.add(tableLabel);

        /*
         * 创建文本域用于用户输入
         */
        JTextField tableText = new JTextField(20);
        tableText.setBounds(100, 20, 165, 25);
        panel.add(tableText);

        // 输入密码的文本域
        JLabel packageLabel = new JLabel("包名:");
        packageLabel.setBounds(10, 50, 80, 25);
        panel.add(packageLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        JTextField packageText = new JTextField(20);
        packageText.setBounds(100, 50, 165, 25);
        panel.add(packageText);

        // 输入密码的文本域
        JLabel modelLabel = new JLabel("模块:");
        modelLabel.setBounds(10, 80, 80, 25);
        panel.add(modelLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        JTextField modelText = new JTextField(20);
        modelText.setBounds(100, 80, 165, 25);
        panel.add(modelText);

        // 生成按钮
        JButton loginButton = new JButton("点击生成");
        loginButton.setBounds(100, 110, 100, 25);


        loginButton.addActionListener(new ActionListener() {




            @Override
            public void actionPerformed(ActionEvent e) {
                String modelName = modelText.getText();
                String entityPackage = packageText.getText();
                String tableName = tableText.getText();
                //判断有没有模块
                String modelPath = AutoCodeConfig.PROJECT_PATH + "\\" + modelName;
                if (!new File(modelPath).exists()){
                    String msg = "生成失败：未找到代码模块！";
                    infoLabel.setForeground(Color.RED);
                    infoLabel.setText(msg);
                    infoLabel.setBounds(100, 140, 120, 25);
                    panel.add(infoLabel);
                    return;
                }
                try {
                    CoderInterface coderInterface = new CoderImpl();
                    MainClass mainClass = coderInterface.getMainClass(tableName,entityPackage,modelName);
                    coderInterface.createController(mainClass);
                    coderInterface.createMapping(mainClass);
                    coderInterface.createMapper(mainClass);
                    coderInterface.createServiceImpl(mainClass);
                    coderInterface.createService(mainClass);
                    coderInterface.createPojo(mainClass);
                    String msg = "生成成功！";
                    infoLabel.setForeground(Color.GREEN);
                    infoLabel.setText(msg);
                    infoLabel.setBounds(100, 140, 120, 25);
                    panel.add(infoLabel);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });
        panel.add(loginButton);

        String projectPath = System.getProperty("user.dir");
        String propertyPath = AutoCodeConfig.PROJECT_PATH.substring(0,AutoCodeConfig.PROJECT_PATH.length()-1);


        String info = "";


        if (!projectPath.equals(propertyPath)){
            System.out.println(projectPath);
            System.out.println(propertyPath);
            info = "路径配置有误！";
            infoLabel.setForeground(Color.RED);
        } else {
            info = "请填写生成信息！";
            infoLabel.setForeground(Color.ORANGE);
        }
        infoLabel.setText(info);
        infoLabel.setBounds(100, 140, 120, 25);
        panel.add(infoLabel);
    }
}
