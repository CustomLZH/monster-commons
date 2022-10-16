package com.monster.commons.generate.enums;


import com.monster.commons.generate.service.VerifyService;

/**
 * 列注解验证枚举
 *
 * @Author: LiuZhaoHong
 * @Date: 2021/8/15
 * @Version: 1.0
 */
public enum ColumnAnnotationVerifyEnum implements
        VerifyService<ColumnAnnotationVerifyEnum, ColumnAnnotationEnum> {

    /**
     * 主键
     */
    PRIMARY_KEY_FORMAT_VERIFY(VerifyEnum.PRIMARY_KEY, null, ColumnAnnotationEnum.PRIMARY_KEY_FORMAT,
            null, null, "Boolean", null),
    /**
     * 非主键
     */
    NOT_PRIMARY_KEY_FORMAT_VERIFY(VerifyEnum.NOT_PRIMARY_KEY, null, ColumnAnnotationEnum.NOT_PRIMARY_KEY_FORMAT,
            null, null, "Boolean", null),

    ;

    /**
     * 判断参数
     */
    private VerifyEnum verifyValue;

    /**
     * 成功的判断
     */
    private ColumnAnnotationVerifyEnum succeedColumnAnnotationVerify;

    /**
     * 成功的引用
     */
    private ColumnAnnotationEnum succeedColumnAnnotation;

    /**
     * 失败的判断
     */
    private ColumnAnnotationVerifyEnum failColumnAnnotationVerify;

    /**
     * 失败的引用
     */
    private ColumnAnnotationEnum failColumnAnnotation;

    /**
     * 判断类型
     */
    private String verifyClass;

    /**
     * 判断类型为String的值
     */
    private String stringVerifyValue;

    ColumnAnnotationVerifyEnum(VerifyEnum verifyValue,
                               ColumnAnnotationVerifyEnum succeedColumnAnnotationVerify,
                               ColumnAnnotationEnum succeedColumnAnnotation,
                               ColumnAnnotationVerifyEnum failColumnAnnotationVerify,
                               ColumnAnnotationEnum failColumnAnnotation,
                               String verifyClass,
                               String stringVerifyValue) {

        this.verifyValue = verifyValue;
        this.succeedColumnAnnotationVerify = succeedColumnAnnotationVerify;
        this.succeedColumnAnnotation = succeedColumnAnnotation;
        this.failColumnAnnotationVerify = failColumnAnnotationVerify;
        this.failColumnAnnotation = failColumnAnnotation;
        this.verifyClass = verifyClass;
        this.stringVerifyValue = stringVerifyValue;
    }

    @Override
    public VerifyEnum getVerifyValue() {
        return verifyValue;
    }

    @Override
    public ColumnAnnotationVerifyEnum getSucceedReference() {
        return succeedColumnAnnotationVerify;
    }

    @Override
    public ColumnAnnotationEnum getSucceedValue() {
        return succeedColumnAnnotation;
    }

    @Override
    public ColumnAnnotationVerifyEnum getFailReference() {
        return failColumnAnnotationVerify;
    }

    @Override
    public ColumnAnnotationEnum getFailValue() {
        return failColumnAnnotation;
    }

    @Override
    public String getVerifyClass() {
        return verifyClass;
    }

    @Override
    public String getStringVerifyValue() {
        return stringVerifyValue;
    }
}
