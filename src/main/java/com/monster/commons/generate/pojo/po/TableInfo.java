package com.monster.commons.generate.pojo.po;

import lombok.Data;

import java.util.List;

/**
 * 表信息
 * @Author: LiuZhaoHong
 * @Date: 2021/8/10
 * @Version: 1.0
 */
@Data
public class TableInfo {


    /**
     * 表名
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 列信息
     */
    private List<ColumnInfo> columnInfos;

}
