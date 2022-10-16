package com.monster.commons.generate.util;


import com.monster.commons.generate.constants.ImportConstant;
import com.monster.commons.generate.enums.*;
import com.monster.commons.generate.pojo.po.ColumnInfo;
import com.monster.commons.generate.service.ConfigureInfoService;
import com.monster.commons.generate.pojo.po.TableInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自动生成实体类
 * @Author: LiuZhaoHong
 * @Date: 2021/8/14
 * @Version: 1.0
 */
public class GenerateEntityUtil {

    /**
     * 正则匹配
     */
    private static Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");

    /**
     * 实体类自动生成
     * @param className 实体类名称
     * @param entityPath 实体类路径
     * @param entityPackage 实体类包位置
     * @param tableInfo 表信息
     * @param convertType 转换类型
     */
    public static void generate(String className, String entityPath, String entityPackage,
                                TableInfo tableInfo, ConvertTypeEnum convertType,
                                ConfigureInfoService configureInfoService,
                                List<ImportVerifyEnum> importVerifyEnumList,
                                List<ClassAnnotationVerifyEnum> classAnnotationVerifyEnumList,
                                List<ColumnAnnotationVerifyEnum> columnAnnotationVerifyEnumList) {
        StringBuilder stringBuffer = new StringBuilder();
        // 所在包位置
        stringBuffer.append(ImportConstant.PACKAGE_NAME).append(SymbolUtil.spaceByOne())
                .append(entityPackage).append(ImportConstant.SEMICOLON).append(SymbolUtil.intervalLineFeed());

        // 引入其他类
        isAddImport(importVerifyEnumList, configureInfoService, stringBuffer);
        stringBuffer.append(SymbolUtil.lineFeedByOne());

        // 类注释
        stringBuffer.append("/**").append(SymbolUtil.lineFeedByOne());
        stringBuffer.append(" * ").append(tableInfo.getTableComment()).append(SymbolUtil.lineFeedByOne());
        stringBuffer.append(" * ").append("@author SystemGenerate").append(SymbolUtil.lineFeedByOne());
        stringBuffer.append(" */").append(SymbolUtil.lineFeedByOne());

        // 类注解
        isAddClassAnnotation(configureInfoService, classAnnotationVerifyEnumList, stringBuffer, tableInfo);

        stringBuffer.append("public class ").append(className);

        stringBuffer.append(" implements Serializable");

        stringBuffer.append(" {");
        stringBuffer.append(SymbolUtil.lineFeedByOne());

        // 列信息
        List<ColumnInfo> columnInfos = tableInfo.getColumnInfos();

        // setter和getter需要的信息
        List<ColumnInfo> noLombok = new ArrayList<>();
        // 字段遍历
        for (ColumnInfo columnInfo : columnInfos) {
            ColumnInfo info = new ColumnInfo();
            // 字段注释
            stringBuffer.append("\t/**\r\n");
            stringBuffer.append("\t * ").append(columnInfo.getColumnComment()).append("\r\n");
            stringBuffer.append("\t */\r\n");

            // 字段类型
            String type = GetTypeUtil.getType(columnInfo.getColumnType(), columnInfo.getColumnSize(), columnInfo.getIsAuto(), convertType);
            info.setColumnType(type);
            // 字段注解
            isAddColumnAnnotation(columnInfo, type, stringBuffer, columnAnnotationVerifyEnumList, configureInfoService);
            // 字段
            String columnName = columnInfo.getColumnName();
            if (configureInfoService.getColumnNamePrefix()) {
                columnName = removePrefix(columnName, configureInfoService.getColumnNamePrefixValue());
            }
            columnName = underline2Camel(columnName);
            stringBuffer.append("\tprivate ").append(type).append(" ").append(columnName)
                    .append(";").append(SymbolUtil.lineFeedByNum(2));
            info.setColumnName(columnName);
            noLombok.add(info);
        }

        // getter和setter
        if (Boolean.FALSE.equals(configureInfoService.getLombok())) {
            for (ColumnInfo columnInfo : noLombok) {
                // getter
                stringBuffer.append("\tpublic ").append(columnInfo.getColumnType())
                        .append(" get").append(firstCapital(columnInfo.getColumnName())).append("() {")
                        .append(SymbolUtil.lineFeedByOne());
                stringBuffer.append(SymbolUtil.getTabByTwo()).append("return ")
                        .append(columnInfo.getColumnName()).append(";").append(SymbolUtil.lineFeedByOne());
                stringBuffer.append(SymbolUtil.getTabByOne()).append("}").append(SymbolUtil.lineFeedByNum(2));

                // setter
                stringBuffer.append(SymbolUtil.getTabByOne()).append("public void set")
                        .append(firstCapital(columnInfo.getColumnName())).append("(").append(columnInfo.getColumnType())
                        .append(SymbolUtil.spaceByOne()).append(columnInfo.getColumnName()).append(") {").append(SymbolUtil.lineFeedByOne());
                stringBuffer.append(SymbolUtil.getTabByTwo()).append("this.").append(columnInfo.getColumnName())
                        .append(" = ").append(columnInfo.getColumnName()).append(";").append(SymbolUtil.lineFeedByOne())
                        .append(SymbolUtil.getTabByOne()).append("}").append(SymbolUtil.lineFeedByNum(2));
            }
        }

        stringBuffer.append("}\r\n");
        String content = stringBuffer.toString();
        System.out.println(content);
        FileWriter fw = null;
        try {
            fw = new FileWriter(entityPath + File.separator + className + ".java");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fw != null;
        PrintWriter pw = new PrintWriter(fw);
        pw.println(content);
        pw.flush();
        
    }

    private static String removePrefix(String columnName, String columnNamePrefixValue) {
        String[] split = columnNamePrefixValue.split(",");
        for (String prefix : split) {
            if (columnName.startsWith(prefix)) {
                return columnName.replace(prefix, "");
            }
        }
        return columnName;
    }

    /**
     * 是否添加列的注解
     * @param columnInfo 列信息
     * @param columnType 列类型
     * @param stringBuffer 写入信息的对象
     * @param asList 列注解验证枚举集合
     */
    private static void isAddColumnAnnotation(ColumnInfo columnInfo, String columnType,
                                              StringBuilder stringBuffer,
                                              List<ColumnAnnotationVerifyEnum> asList,
                                              ConfigureInfoService configureInfoService) {
        for (ColumnAnnotationVerifyEnum columnAnnotationVerifyEnum : asList) {
            // 判断枚举
            VerifyEnum verifyValue = columnAnnotationVerifyEnum.getVerifyValue();
            switch (verifyValue) {
                // 表名
                case TABLE_NAME:
                    break;
                // 表注释
                case TABLE_COMMENT:
                    break;
                // 列注释
                case COLUMN_COMMENT:
                    break;
                // 列类型
                case COLUMN_TYPE:
                    if ("String".equalsIgnoreCase(columnAnnotationVerifyEnum.getVerifyClass())) {
                        if (columnAnnotationVerifyEnum.getVerifyValue().equals(columnType)) {
                            stringBuffer.append(SymbolUtil.getTabByOne());
                            stringBuffer.append(columnAnnotationVerifyEnum.getSucceedValue().getFormat());
                            stringBuffer.append(SymbolUtil.lineFeedByOne());
                        }
                    }
                    break;
                // 主键
                case PRIMARY_KEY:
                    if (Boolean.TRUE.equals(configureInfoService.getMyBatisPlusTableName()) && Boolean.TRUE.equals(columnInfo.getIsPrimaryKey())) {
                        stringBuffer.append(SymbolUtil.getTabByOne());
                        stringBuffer.append(String.format(columnAnnotationVerifyEnum.getSucceedValue().getFormat(),
                                getSocietyColumnValue(columnInfo, columnAnnotationVerifyEnum.getSucceedValue().getType())));
                        stringBuffer.append(SymbolUtil.lineFeedByOne());
                    }
                    break;
                // 非主键
                case NOT_PRIMARY_KEY:
                    if (Boolean.TRUE.equals(configureInfoService.getMyBatisPlusTableName()) && Boolean.FALSE.equals(columnInfo.getIsPrimaryKey())) {
                        stringBuffer.append(SymbolUtil.getTabByOne());
                        stringBuffer.append(String.format(columnAnnotationVerifyEnum.getSucceedValue().getFormat(),
                                getSocietyColumnValue(columnInfo, columnAnnotationVerifyEnum.getSucceedValue().getType())));
                        stringBuffer.append(SymbolUtil.lineFeedByOne());
                    }
                    break;
                // 自增
                case AUTO_ADD:
                    break;
                // 不为空
                case NOT_NULL:
                    break;
                default:
                    throw new IllegalStateException("没有这个类型 -> " + verifyValue);
            }
        }
    }


    /**
     * 是否添加类的注解
     * @param configureInfoService 配置信息
     * @param classAnnotationVerifyEnumList 注解集合
     * @param stringBuffer 写入信息的对象
     * @param tableInfo 表信息
     */
    private static void isAddClassAnnotation(ConfigureInfoService configureInfoService, List<ClassAnnotationVerifyEnum> classAnnotationVerifyEnumList, StringBuilder stringBuffer, TableInfo tableInfo) {
        for (ClassAnnotationVerifyEnum classAnnotationVerifyEnum : classAnnotationVerifyEnumList) {
            // 判断枚举
            VerifyEnum verifyValue = classAnnotationVerifyEnum.getVerifyValue();

            switch (verifyValue) {
                case TABLE_NAME:
                    break;
                case TABLE_COMMENT:
                    break;
                case COLUMN_NAME:
                    break;
                case COLUMN_COMMENT:
                    break;
                case COLUMN_TYPE:
                    break;
                case PRIMARY_KEY:
                    break;
                case AUTO_ADD:
                    break;
                case NOT_NULL:
                    break;
                case LOMBOK_DATA:
                    if (Boolean.TRUE.equals(configureInfoService.getLombok())) {
                        stringBuffer.append(classAnnotationVerifyEnum.getSucceedValue().getFormat())
                                .append(SymbolUtil.lineFeedByOne());
                    }
                    break;
                case MYBATIS_PLUS_TABLE_NAME:
                    if (Boolean.TRUE.equals(configureInfoService.getMyBatisPlusTableName())) {
                        stringBuffer.append(String.format(classAnnotationVerifyEnum.getSucceedValue().getFormat(),
                                getSocietyValue(classAnnotationVerifyEnum.getSucceedValue().getType(), tableInfo)));
                        stringBuffer.append(SymbolUtil.lineFeedByOne());
                    }
                default:
                    break;
            }
//
//            stringBuffer.append(String.format(classAnnotationVerifyEnum.getAnnotationFormat(),
//                    getSocietyValue(classAnnotationVerifyEnum.getTableValue(), tableInfo))).append(SymbolUtil.lineFeedByOne());
        }
    }

    /**
     * 获取表详细信息
     * @param verifyEnum 表枚举
     * @param tableInfo 表信息
     * @return
     */
    private static String getSocietyValue(VerifyEnum verifyEnum, TableInfo tableInfo) {
        if (verifyEnum == null) return null;
        switch (verifyEnum) {
            case TABLE_NAME:
                return tableInfo.getTableName();
            case TABLE_COMMENT:
                return tableInfo.getTableComment();
            default:
                return null;
        }
    }

    /**
     * 获取列详细信息
     * @param columnInfo 列信息
     * @param tableValue 表枚举
     * @return
     */
    private static String getSocietyColumnValue(ColumnInfo columnInfo, VerifyEnum tableValue) {
        if (columnInfo == null) return null;
        switch (tableValue) {
            case COLUMN_NAME:
                return columnInfo.getColumnName();
            default:
                return null;
        }
    }

    /**
     * 是否添加引用
     * @param importVerifyEnumList 表有的导入包路径集合
     * @param stringBuffer 写入信息的对象
     */
    private static void isAddImport(List<ImportVerifyEnum> importVerifyEnumList,
                                    ConfigureInfoService configureInfoService,
                                    StringBuilder stringBuffer) {
        for (ImportVerifyEnum importVerifyEnum : importVerifyEnumList) {
            VerifyEnum verifyValue = importVerifyEnum.getVerifyValue();
            switch (verifyValue) {

                case TABLE_NAME:
                    break;
                case TABLE_COMMENT:
                    break;
                case COLUMN_NAME:
                    break;
                case COLUMN_COMMENT:
                    break;
                case COLUMN_TYPE:
                    break;
                case PRIMARY_KEY:
                    break;
                case NOT_PRIMARY_KEY:
                    break;
                case AUTO_ADD:
                    break;
                case NOT_NULL:
                    break;
                case LOMBOK_DATA:
                    if (Boolean.TRUE.equals(configureInfoService.getLombok())) {
                        stringBuffer.append(importVerifyEnum.getSucceedImport().getFormat())
                                .append(SymbolUtil.lineFeedByOne());
                    }
                    break;
                case MYBATIS_PLUS_TABLE_NAME:
                    if (Boolean.TRUE.equals(configureInfoService.getMyBatisPlusTableName())) {
                        stringBuffer.append(importVerifyEnum.getSucceedImport().getFormat())
                                .append(SymbolUtil.lineFeedByOne());
                    }
                    break;
                case DATE_TYPE:
                    stringBuffer.append(importVerifyEnum.getSucceedImport().getFormat())
                            .append(SymbolUtil.lineFeedByOne());
                    break;
                case SERIALIZABLE:
                    stringBuffer.append(importVerifyEnum.getSucceedImport().getFormat())
                            .append(SymbolUtil.lineFeedByOne());
                    break;
                default:
                    break;
            }
        }
    }


    /**
     * 下划线转首字母大写
     * @param line 字符串
     * @return 返回驼峰命名
     */
    public static String underline2Camel(String line) {
        if (line == null || "".equals(line)) return "";
        StringBuilder sb = new StringBuilder();
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(matcher.start() == 0 ? Character.toLowerCase(word.charAt(0)) : Character.toUpperCase(word.charAt(0)));
            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * 首字母大写
     * @param line 字符串
     * @return 返回首字母大写字符串
     */
    public static String firstCapital(String line) {
        if (line == null || "".equals(line)) return "";
        return line.substring(0, 1).toUpperCase() + line.substring(1);
    }
}
