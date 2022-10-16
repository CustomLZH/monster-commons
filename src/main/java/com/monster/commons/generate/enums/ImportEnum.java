package com.monster.commons.generate.enums;

import com.monster.commons.generate.service.TargetService;

/**
 * 基础导入包
 * @Author: LiuZhaoHong
 * @Date: 2021/8/14
 * @Version: 1.0
 */
public enum ImportEnum implements TargetService<Boolean> {

    /**
     * MyBatis-Plus
     */
    MY_BATIS_PLUS_ID_TYPE("import com.baomidou.mybatisplus.annotation.IdType;")
    ,

    MY_BATIS_PLUS_TABLE_ID("import com.baomidou.mybatisplus.annotation.TableId;"),

    MY_BATIS_PLUS_TABLE_FIELD("import com.baomidou.mybatisplus.annotation.TableField;"),

    MY_BATIS_PLUS_TABLE_NAME("import com.baomidou.mybatisplus.annotation.TableName;"),


    /**
     * Lombok-Data
     */
    LOMBOK_DATA("import lombok.Data;"),

    /**
     * 日期类型
     */
    DATE_TYPE_IMPORT("import java.util.Date;"),


    /**
     * 序列化
     */
    SERIALIZABLE_IMPORT("import java.io.Serializable;"),



    ;

    /**
     * 引用
     */
    private final String importValue;


    ImportEnum(String importValue) {
        this.importValue = importValue;
    }


    @Override
    public String getFormat() {
        return importValue;
    }

    @Override
    public Boolean getType() {
        return null;
    }
}
