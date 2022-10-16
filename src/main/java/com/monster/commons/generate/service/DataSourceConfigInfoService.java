package com.monster.commons.generate.service;

/**
 * @Author: LiuZhaoHong
 * @Date: 2021/8/13
 * @Version: 1.0
 */
public interface DataSourceConfigInfoService {

    /**
     * oracle地址
     */
    String ORACLE_HOST = "localhost";
    /**
     * oracle端口
     */
    String ORACLE_PORT = "1521";
    /**
     * oracle数据库url格式
     */
    String ORACLE_URL_FORMAT = "jdbc:oracle:thin:@%s:%s:%s";
    /**
     * oracle驱动
     */
    String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";


    
    /**
     * mysql数据库url格式
     */
    String MYSQL_URL_FORMAT = "jdbc:mysql://%s:%s/%s?useSSL=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    /**
     * mysql驱动
     */
    String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    /**
     * mysql地址
     */
    String MYSQL_HOST = "localhost";
    /**
     * mysql端口
     */
    String MYSQL_PORT = "3306";


    /**
     * 获取OracleHost
     * @return
     */
    default String getOracleHost() {
        return ORACLE_HOST;
    }

    /**
     * 获取OraclePort
     * @return
     */
    default String getOraclePort() {
        return ORACLE_PORT;
    }

    /**
     * 获取OracleDatabase
     * @return
     */
    String getOracleDatabase();

    /**
     * 获取OracleUsername
     * @return
     */
    String getOracleUsername();

    /**
     * 获取OraclePassword
     * @return
     */
    String getOraclePassword();

    /**
     * 获取OracleUrlFormat
     * @return
     */
    default String getOracleUrlFormat() {
        return ORACLE_URL_FORMAT;
    }

    /**
     * 获取OracleDriver
     * @return
     */
    default String getOracleDriver() {
        return ORACLE_DRIVER;
    }


    /**
     * 获取MysqlHost
     * @return
     */
    default String getMysqlHost() {
        return MYSQL_HOST;
    }

    /**
     * 获取MysqlPort
     * @return
     */
    default String getMysqlPort() {
        return MYSQL_PORT;
    }

    /**
     * 获取MysqlDatabase
     * @return
     */
    String getMysqlDatabase();

    /**
     * 获取MysqlUsername
     * @return
     */
    String getMysqlUsername();

    /**
     * 获取MysqlPassword
     * @return
     */
    String getMysqlPassword();

    /**
     * 获取MysqlUrlFormat
     * @return
     */
    default String getMysqlUrlFormat() {
        return MYSQL_URL_FORMAT;
    }

    /**
     * 获取MysqlDriver
     * @return
     */
    default String getMysqlDriver() {
        return MYSQL_DRIVER;
    }

}
