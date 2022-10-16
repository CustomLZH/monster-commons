package com.monster.commons.generate.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

/**
 * @Author: LiuZhaoHong
 * @Date: 2021/8/12
 * @Version: 1.0
 */
public class JdbcUtil {

    private static Connection connection;

    private static Statement statement;

    public static void initialize(String url, String user, String password) {
        if (Objects.isNull(connection)) {
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        if (Objects.isNull(statement)) {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return statement;
    }

    public static void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
