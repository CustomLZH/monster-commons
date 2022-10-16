package com.monster.commons.generate.enums;


import com.monster.commons.generate.service.TargetService;

/**
 * @Author: LiuZhaoHong
 * @Date: 2021/8/15
 * @Version: 1.0
 */
public enum ClassAnnotationEnum implements TargetService<VerifyEnum> {

    /**
     * MyBatisPlus匹配表名注解
     */
    MYBATIS_PLUS_TABLE_NAME("@TableName(\"%s\")", VerifyEnum.TABLE_NAME),

    /**
     * lombok生成get与set
     */
    LOMBOK_DATA("@Data", null),

    ;

    private final String annotationFormat;

    private final VerifyEnum tableValue;

    ClassAnnotationEnum(String annotationFormat, VerifyEnum verifyEnum) {
        this.annotationFormat = annotationFormat;
        this.tableValue = verifyEnum;
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
