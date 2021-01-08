package com.isabella.lee.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private DateUtil() {}

    private static DateUtil dateUtil = new DateUtil();

    public static DateUtil getInstance() {
        if (dateUtil == null) {
            dateUtil = new DateUtil();
        }
        return dateUtil;
    }


    private final String YYYY = "yyyy";
    private final String YYYY_MM = "yyyy-MM";
    private final String YYYY_MM_DD = "yyyy-MM-dd";
    private final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    private final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";


    //时间格式化
    public final String parseDate(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public final String YYYY(final Date date) {
        return parseDate(YYYY, date);
    }

    public final String YYYY_MM(final Date date) {
        return parseDate(YYYY_MM, date);
    }


    public final String YYYY_MM_DD(final Date date) {
        return parseDate(YYYY_MM_DD, date);
    }

    public final String YYYYMMDDHHMMSS(final Date date) {
        return parseDate(YYYYMMDDHHMMSS, date);
    }

    public final String YYYY_MM_DD_HH_MM_SS(final Date date) {
        return parseDate(YYYY_MM_DD_HH_MM_SS, date);
    }
}
