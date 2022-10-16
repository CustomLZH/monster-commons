package com.monster.commons.generate.util;


import com.monster.commons.generate.enums.ConvertTypeEnum;

import java.util.Locale;

/**
 * @Author: LiuZhaoHong
 * @Date: 2021/8/13
 * @Version: 1.0
 */
public class GetTypeUtil {

    public static String getType(String colType, Integer colTypeLen, Boolean colAuto, ConvertTypeEnum typeEnum) {
        colType = colType.toUpperCase(Locale.ROOT);
        int i = colType.indexOf("(");
        if (i != -1) {
            colType = colType.substring(0, i);
        }
        String type = null;
        switch (typeEnum) {
            case MYSQL_TO_JAVA:
                type = getTypeMySqlToJava(colType);
                break;
            case MYSQL_TO_ORACLE:
                type = getTypeMySqlToOracle(colType);
                break;
            case ORACLE_TO_JAVA:
                type = getTypeOracleToJava(colType);
                break;
            case ORACLE_TO_MYSQL:
                type = getTypeOracleToMysql(colType, colTypeLen, colAuto);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + typeEnum);
        }
        return type;
    }


    /**
     * 根据MySql类型获取Java类型
     * @param colType
     * @return
     */
    public static String getTypeMySqlToJava(String colType) {
        switch (colType) {
            case "TINYINT":
                return "Integer";
            case "SMALLINT":
                return "Integer";
            case "INT":
                return "Integer";
            case "INTEGER":
                return "Integer";
            case "BIGINT":
                return "Long";
            case "FLOAT":
                return "Float";
            case "DOUBLE":
            case "NUMERIC":
                return "Double";
            case "DECIMAL":
                return "BigDecimal";
            case "VARCHAR":
            case "TEXT":
            case "CHAR":
            case "LONGTEXT":
            case "JSON":
                return "String";
            case "DATE":
            case "TIME":
            case "DATETIME":
            case "TIMESTAMP":
                return "Date";
            case "BIT":
                return "Boolean";
            case "BLOB":
                return "Byte[]";
            default:
                System.out.println("ERROR DATA TYPE : " + colType);
                return null;
        }
    }

    /**
     * 根据MySql类型获取Oracle类型（未测试）
     * @param colType
     * @return
     */
    public static String getTypeMySqlToOracle(String colType) {
        switch (colType) {
            case "TINYINT":
                return "Integer";
            case "SMALLINT":
                return "Integer";
            case "INT":
                return "Integer";
            case "INTEGER":
                return "Integer";
            case "BIGINT":
                return "Long";
            case "FLOAT":
                return "Float";
            case "DOUBLE":
                return "Double";
            case "NUMERIC":
                return "Double";
            case "DECIMAL":
                return "BigDecimal";
            case "VARCHAR":
            case "TEXT":
            case "MEDIUMTEXT":
            case "VARCHAR2":
            case "CHAR":
                return "String";
            case "DATE":
                return "Date";
            case "TIME":
            case "DATETIME":
            case "TIMESTAMP":
                return "Date";
            case "JSON":
                return "String";
            case "BIT":
                return "Boolean";
            default:
                System.out.println("ERROR DATA TYPE : " + colType);
                return null;
        }
    }


    /**
     * 根据Oracle类型获取Java类型
     * @param colType
     * @return
     */
    public static String getTypeOracleToJava(String colType) {
        switch (colType) {
            case "VARCHAR2":
            case "NVARCHAR2":
            case "CHAR":
            case "LONG":
            case "CLOB":
                return "String";
            case "DATE":
            case "TIMESTAMP":
                return "Date";
            case "NUMBER":
            case "NUMBER2":
                return "Integer";
            case "BLOB":
                return "Byte[]";
            default:
                System.out.println("ERROR DATA TYPE : " + colType);
                return null;
        }
    }


    /**
     * 根据Oracle类型获取Mysql类型
     */
    public static String getTypeOracleToMysql(String colType, Integer colTypeLen, Boolean colAuto) {
        switch (colType) {
            case "VARCHAR2":
                if (colAuto) {
                    return "bigint";
                }
                if (colTypeLen > 255) {
                    return "text";
                }
                return "varchar";
            case "CHAR":
            case "LONG":
            case "NVARCHAR2":
                if (colTypeLen > 255) {
                    return "text";
                }
                return "varchar";
            case "DATE":
            case "TIMESTAMP":
                return "datetime";
            case "NUMBER":
            case "NUMBER2":
                if (colTypeLen > 11) {
                    return "bigint";
                }
                return "tinyint";
            case "BLOB":
                return "blob";
            case "CLOB":
                return "longtext";
            default:
                System.out.println("ERROR DATA TYPE : " + colType);
                return null;
        }
    }

}
