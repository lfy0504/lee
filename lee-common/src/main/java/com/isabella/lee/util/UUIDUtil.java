package com.isabella.lee.util;

import java.util.UUID;

public class UUIDUtil {
    private UUIDUtil() {
    }

    private static UUIDUtil uuidUtil = new UUIDUtil();

    public static UUIDUtil getInstance() {
        if (uuidUtil == null) {
            uuidUtil = new UUIDUtil();
        }
        return uuidUtil;
    }

    public String getUUID(int number) {
        if (number > 32)
            number = 32;
        String uuid = UUID.randomUUID().toString().toLowerCase().replace("-", "");
        return uuid.substring(0, number);
    }

}
