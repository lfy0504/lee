package com.isabella.lee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@MapperScan("com.isabella.lee.*.mapper")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class LeeApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeeApplication.class, args);
    }
}
