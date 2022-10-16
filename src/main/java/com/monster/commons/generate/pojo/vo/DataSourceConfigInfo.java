package com.monster.commons.generate.pojo.vo;

import lombok.Data;

/**
 * 数据源配置信息
 * @Author: LiuZhaoHong
 * @Date: 2021/8/10
 * @Version: 1.0
 */
@Data
public class DataSourceConfigInfo {
    /**
     * oracle地址
     */
    public String oracleHost = "localhost";
    /**
     * oracle端口
     */
    public String oraclePort = "1521";
    /**
     * oracle数据库名
     */
    public String oracleDatabase;
    /**
     * oracle用户名
     */
    public String oracleUsername;
    /**
     * oracle密码
     */
    public String oraclePassword;
    /**
     * oracle数据库url格式
     */
    public String oracleUrlFormat = "jdbc:oracle:thin:@%s:%s:%s";
    /**
     * oracle驱动
     */
    public static String oracleDriver = "oracle.jdbc.driver.OracleDriver";



    /**
     * mysql地址
     */
    public String mysqlHost = "localhost";
    /**
     * mysql端口
     */
    public String mysqlPort = "3306";
    /**
     * mysql数据库名
     */
    public String mysqlDatabase;
    /**
     * mysql用户名
     */
    public String mysqlUsername;
    /**
     * mysql密码
     */
    public String mysqlPassword;
    /**
     * mysql数据库url格式
     */
    public String mysqlUrlFormat = "jdbc:mysql://%s:%s/%s?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=CTT";
    /**
     * mysql驱动
     */
    public static String mysqlDriver = "com.mysql.cj.jdbc.Driver";


}
