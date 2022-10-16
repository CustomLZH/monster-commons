package com.monster.commons.generate.enums;


import com.monster.commons.generate.service.TargetService;

/**
 * @Author: LiuZhaoHong
 * @Date: 2021/8/15
 * @Version: 1.0
 */
public enum ColumnAnnotationEnum implements TargetService<VerifyEnum> {


    /**
     * LONG的JSON格式化
     */
    LONG_JSON_FORMAT("@JsonFormat(shape = JsonFormat.Shape.STRING)", null),
    /**
     * 主键
     */
    PRIMARY_KEY_FORMAT("@TableId(value = \"%s\", type = IdType.ASSIGN_ID)", VerifyEnum.COLUMN_NAME),
    /**
     * 主键
     */
    NOT_PRIMARY_KEY_FORMAT("@TableField(value = \"%s\")", VerifyEnum.COLUMN_NAME),

    ;

    /**
     * 引用
     */
    private final String annotationFormat;

    /**
     * 格式化中需要加入的表的数据
     */
    private final VerifyEnum tableValue;

    ColumnAnnotationEnum(String annotationFormat, VerifyEnum tableValue) {
        this.annotationFormat = annotationFormat;
        this.tableValue = tableValue;
    }

    @Override
    public String getFormat() {
        return annotationFormat;
    }

    @Override
    public VerifyEnum getType() {
        return tableValue;
    }
}
