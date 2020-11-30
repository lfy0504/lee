package com.isabella.lee.util;

import javax.print.DocFlavor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoderUtil {

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 首字母大写
     */
    public static String upperFirst(String str) {
        char[] chars = str.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] = (char) (chars[0] - 32);
        }
        return new String(chars);
    }


    /**
     * 数据类型转化JAVA
     *
     * @param sqlType：类型名称
     * @return
     */
    public static String jdbcToJava(String sqlType) {
        if (sqlType == null || sqlType.trim().length() == 0) return sqlType;
        sqlType = sqlType.toLowerCase();
        switch (sqlType) {
            case "nvarchar":
                return "String";
            case "char":
                return "String";
            case "varchar":
                return "String";
            case "text":
                return "String";
            case "nchar":
                return "String";
            case "blob":
                return "Byte[]";
            case "integer":
                return "Integer";
            case "tinyint":
                return "Byte";
            case "smallint":
                return "Integer";
            case "int":
                return "Integer";
            case "mediumint":
                return "Integer";
            case "bit":
                return "Boolean";
            case "bigint":
                return "BigInteger";
            case "float":
                return "Float";
            case "double":
                return "Double";
            case "decimal":
                return "BigDecimal";
            case "boolean":
                return "Boolean";
            case "id":
                return "Long";
            case "date":
                return "Date";
            case "datetime":
                return "Date";
            case "year":
                return "Date";
            case "time":
                return "Time";
            case "timestamp":
                return "Timestamp";
            case "numeric":
                return "BigDecimal";
            case "real":
                return "BigDecimal";
            case "money":
                return "Double";
            case "smallmoney":
                return "Double";
            case "image":
                return "byte[]";
            default:
                System.out.println("-----------------》转化失败：未发现的类型" + sqlType);
                break;
        }
        return sqlType;
    }

}
