package com.monster.commons.generate.util;



import com.monster.commons.generate.enums.ConvertTypeEnum;
import com.monster.commons.generate.service.DataSourceConfigInfoService;
import com.monster.commons.generate.pojo.po.ColumnInfo;
import com.monster.commons.generate.pojo.po.TableInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LiuZhaoHong
 * @Date: 2021/8/11
 * @Version: 1.0
 */
public class InitializeTableInfoUtil {

    /**
     * 获取mysql表信息sql格式
     */
    private static String mysqlGetTableInfoSqlFormat =
            "select t.table_comment, c.column_name, c.COLUMN_DEFAULT, c.data_type, \n" +
            "c.character_maximum_length, c.numeric_precision, c.numeric_scale, \n" +
            "c.is_nullable, c.column_key, c.column_comment\n" +
            "from information_schema.tables t\n" +
            "left join information_schema.columns c\n" +
            "on t.table_schema = c.table_schema\n" +
            "and t.table_name = c.table_name\n" +
            "where t.table_schema = '%s'\n" +
            "and t.table_name = '%s'\n" +
            "order by c.ordinal_position";

    /**
     * 获取oracle表信息sql格式
     */
    private static String oracleTableInfoSqlFormat =
            "select utm.comments, utc.column_name, utc.data_default, utc.data_type, \n" +
            "utc.char_col_decl_length, utc.data_precision, utc.data_scale, utc.nullable \n" +
            "case when utc.column_name in ( \n" +
            "    select col.column_name \n" +
            "    from user_constraints con,user_cons_columns col \n" +
            "    where con.constraint_name=col.constraint_name \n" +
            "    and con.constraint_type='P' \n" +
            "    and col.table_name='%s' \n" +
            ") then 'y' else 'n' end as primary_key, \n" +
            "ucc.comments as column_comment\n" +
            "from user_tab_columns utc\n" +
            "left join user_col_comments ucc\n" +
            "on utc.table_name = ucc.table_name\n" +
            "and utc.column_name = ucc.column_name\n" +
            "left join user_tab_comments utm\n" +
            "on utc.table_name = utm.table_name\n" +
            "where utc.table_name = '%s'\n" +
            "order by utc.column_id";


    private static ConvertTypeEnum convertTypeEnum;

    /**
     * 获取表信息
     * @param tableName 表名称
     * @param info 数据库信息
     * @param convertType 转换类型
     * @return
     */
    protected static TableInfo getTableInfo(String tableName, DataSourceConfigInfoService info, ConvertTypeEnum convertType) {
        String url = null;
        convertTypeEnum = convertType;
        switch (convertType) {
            case MYSQL_TO_JAVA:
            case MYSQL_TO_ORACLE:
                url = String.format(info.getMysqlUrlFormat(), info.getMysqlHost(), info.getMysqlPort(), info.getMysqlDatabase());
                JdbcUtil.initialize(url, info.getMysqlUsername(), info.getMysqlPassword());
                return getMySqlTableInfo(tableName, info);
            default:
                url = String.format(info.getOracleUrlFormat(), info.getOracleHost(), info.getOraclePort(), info.getOracleDatabase());
                JdbcUtil.initialize(url, info.getOracleUsername(), info.getOraclePassword());
                return getOracleTableInfo(tableName, info);
        }
    }

    /**
     * 获取MySQL表信息
     * @param tableName 表名称
     * @param info 数据库连接信息
     * @return
     */
    private static TableInfo getMySqlTableInfo(String tableName, DataSourceConfigInfoService info) {
        TableInfo tableInfo = new TableInfo();
        tableInfo.setTableName(tableName);
        String sql = String.format(mysqlGetTableInfoSqlFormat, info.getMysqlDatabase(), tableName);
        try {
            ResultSet resultSet = JdbcUtil.getStatement().executeQuery(sql);
            parseTableInfo(tableInfo, resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableInfo;
    }


    private static TableInfo getOracleTableInfo(String tableName, DataSourceConfigInfoService info) {

        return null;
    }

    /**
     * 解析表信息
     * @param tableInfo
     * @param resultSet
     */
    private static void parseTableInfo(TableInfo tableInfo, ResultSet resultSet) {
        try {
            boolean flag = true;
            List<ColumnInfo> columnInfos = new ArrayList<>();
            while (resultSet.next()) {
                if (flag) {
                    tableInfo.setTableComment(resultSet.getString(1));
                    flag = false;
                }
                ColumnInfo columnInfo = new ColumnInfo();

                columnInfo.setColumnName(resultSet.getString(2));
                columnInfo.setColumnDefaultValue(resultSet.getString(3));
                columnInfo.setColumnSize(resultSet.getString(5) != null ? resultSet.getInt(5) : resultSet.getInt(6));
                columnInfo.setColumnScaleSize(resultSet.getInt(7));
                columnInfo.setIsNull(getIsNull(resultSet.getString(8)));
                columnInfo.setIsPrimaryKey(getPrimaryKey(resultSet.getString(9)));
                columnInfo.setColumnComment(resultSet.getString(10));
                columnInfo.setIsAuto(getIdTypeAuto(columnInfo.getColumnDefaultValue()));
                columnInfo.setColumnType(resultSet.getString(4));

                columnInfos.add(columnInfo);
            }
            tableInfo.setColumnInfos(columnInfos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Boolean getIsNull(String isNull) {
        if (isNull == null) return false;
        return isNull.contains("N") || isNull.contains("NO");
    }

    public static Boolean getIdTypeAuto(String dataDefault) {
        if (dataDefault == null) return false;
        return dataDefault.contains("sys_guid()") || dataDefault.contains("auto_increment");
    }

    public static Boolean getPrimaryKey(String primaryKey) {
        if (primaryKey == null) return false;
        return primaryKey.contains("Y") || primaryKey.contains("PRI");
    }



}
