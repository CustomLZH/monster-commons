package com.monster.commons.generate.util;


import com.monster.commons.generate.enums.ClassAnnotationVerifyEnum;
import com.monster.commons.generate.enums.ColumnAnnotationVerifyEnum;
import com.monster.commons.generate.enums.ConvertTypeEnum;
import com.monster.commons.generate.enums.ImportVerifyEnum;
import com.monster.commons.generate.service.ConfigureInfoService;
import com.monster.commons.generate.service.DataSourceConfigInfoService;
import com.monster.commons.generate.pojo.po.TableInfo;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: LiuZhaoHong
 * @Date: 2021/8/13
 * @Version: 1.0
 */
public class GenerateUtil {

    /**
     * 绝对路径
     */
    private static String entityPath;

    /**
     * 包路径
     */
    private static String entityPackage;

    /**
     * 自动生成
     * @param map 生成表名称和实体类名称
     * @param dataSource 数据库配置信息
     * @param convertType 转换类型
     * @param projectPath 项目地址
     * @param packageModulePath 包模块地址
     * @param classPackagePath 生成类位置
     * @param configureInfoService 配置信息
     */
    public static void generate(Map<String, String> map, DataSourceConfigInfoService dataSource, ConvertTypeEnum convertType,
                                String projectPath, String packageModulePath, String classPackagePath,
                                ConfigureInfoService configureInfoService,
                                List<ImportVerifyEnum> importVerifyEnumList,
                                List<ClassAnnotationVerifyEnum> classAnnotationVerifyEnumList,
                                List<ColumnAnnotationVerifyEnum> columnAnnotationVerifyEnumList) {
        // 实体类存放路径
        entityPath = projectPath + File.separator + packageModulePath + File.separator + classPackagePath;
        // 实体类导入包路径
        entityPackage = classPackagePath.replaceAll("/", ".");

        // 遍历表
        for (Map.Entry<String, String> entry : map.entrySet()) {
            // 生成实体类
            generate(
                    entry.getKey(),
                    entry.getValue(),
                    dataSource,
                    convertType,
                    configureInfoService,
                    importVerifyEnumList,
                    classAnnotationVerifyEnumList,
                    columnAnnotationVerifyEnumList
            );
        }
        JdbcUtil.close();
    }

    /**
     * 自动生成调度
     * @param tableName 表名
     * @param className 类名
     * @param dataSource 数据库连接信息
     * @param convertType 转换类型
     * @param configureInfoService 配置信息
     */
    private static void generate(String tableName, String className, DataSourceConfigInfoService dataSource,
                                 ConvertTypeEnum convertType, ConfigureInfoService configureInfoService,
                                 List<ImportVerifyEnum> importVerifyEnumList,
                                 List<ClassAnnotationVerifyEnum> classAnnotationVerifyEnumList,
                                 List<ColumnAnnotationVerifyEnum> columnAnnotationVerifyEnumList) {
        // 获取表基础信息
        TableInfo tableInfo = InitializeTableInfoUtil.getTableInfo(tableName, dataSource, convertType);
        // 断言表信息不为空
        assert !Objects.isNull(tableInfo);
        // 实体类生成
        GenerateEntityUtil.generate(
                className,
                entityPath,
                entityPackage,
                tableInfo,
                convertType,
                configureInfoService,
                importVerifyEnumList,
                classAnnotationVerifyEnumList,
                columnAnnotationVerifyEnumList
        );

    }


}
