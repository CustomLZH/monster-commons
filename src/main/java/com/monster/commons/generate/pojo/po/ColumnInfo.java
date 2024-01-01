package com.monster.commons.generate.pojo.po;

import lombok.Data;

/**
 * 列信息
 * @author LiuZhaoHong
 * @version 1.0
 * @date 2021/8/11
 * @since JDK1.8
 */
@Data
public class ColumnInfo {

    /***
     * 列名
     */
    private String columnName;

    /**
     * 列类型
     */
    private String columnType;

    /**
     * 列总大小
     */
    private Integer columnSize;

    /**
     * 列小数大小
     */
    private Integer columnScaleSize;

    /**
     * 列默认值
     */
    private String columnDefaultValue;

    /**
     * 列注释
     */
    private String columnComment;

    /**
     * 是否为空
     */
    private Boolean isNull;

    /**
     * 是否自增
     */
    private Boolean isAuto;

    /**
     * 是否主键
     */
    private Boolean isPrimaryKey;



}
