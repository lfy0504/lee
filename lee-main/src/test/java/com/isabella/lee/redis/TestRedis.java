package com.isabella.lee.redis;

import com.isabella.lee.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
    @Autowired
    RedisUtil redisUtil;

    @Test
    public void testRedis(){
        redisUtil.set("test","这是一条测试redis");
        System.out.println(redisUtil.get("test"));
    }
}
