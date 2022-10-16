package com.monster.commons.generate.enums;


import com.monster.commons.generate.service.VerifyService;

/**
 * @author LiuZhaoHong
 * @version 1.0
 * @date 2022/10/11 22:08
 * @since JDK1.8
 */
public enum ClassAnnotationVerifyEnum implements
        VerifyService<ClassAnnotationVerifyEnum, ClassAnnotationEnum> {

    /**
     * Lombok
     */
    LOMBOK_DATA_VERIFY(VerifyEnum.LOMBOK_DATA, null, ClassAnnotationEnum.LOMBOK_DATA,
            null, null, "Boolean", null),

    /**
     * MyBatisPlus的注解
     */
    MYBATIS_PLUS_TABLE_NAME_VERIFY(VerifyEnum.MYBATIS_PLUS_TABLE_NAME, null, ClassAnnotationEnum.MYBATIS_PLUS_TABLE_NAME,
            null, null, "Boolean", null),
    ;

    /**
     * 判断参数
     */
    private VerifyEnum verifyValue;

    /**
     * 成功的判断
     */
    private ClassAnnotationVerifyEnum succeedClassAnnotationVerify;

    /**
     * 成功的引用
     */
    private ClassAnnotationEnum succeedClassAnnotation;

    /**
     * 失败的判断
     */
    private ClassAnnotationVerifyEnum failClassAnnotationVerify;

    /**
     * 失败的引用
     */
    private ClassAnnotationEnum failClassAnnotation;

    /**
     * 判断类型
     */
    private String verifyClass;

    /**
     * 判断类型为String的值
     */
    private String stringVerifyValue;

    ClassAnnotationVerifyEnum(VerifyEnum verifyValue,
                              ClassAnnotationVerifyEnum succeedClassAnnotationVerify,
                              ClassAnnotationEnum succeedClassAnnotation,
                              ClassAnnotationVerifyEnum failClassAnnotationVerify,
                              ClassAnnotationEnum failClassAnnotation,
                              String verifyClass,
                              String stringVerifyValue) {

        this.verifyValue = verifyValue;
        this.succeedClassAnnotationVerify = succeedClassAnnotationVerify;
        this.succeedClassAnnotation = succeedClassAnnotation;
        this.failClassAnnotationVerify = failClassAnnotationVerify;
        this.failClassAnnotation = failClassAnnotation;
        this.verifyClass = verifyClass;
        this.stringVerifyValue = stringVerifyValue;
    }


    @Override
    public VerifyEnum getVerifyValue() {
        return verifyValue;
    }

    @Override
    public ClassAnnotationVerifyEnum getSucceedReference() {
        return succeedClassAnnotationVerify;
    }

    @Override
    public ClassAnnotationEnum getSucceedValue() {
        return succeedClassAnnotation;
    }

    @Override
    public ClassAnnotationVerifyEnum getFailReference() {
        return failClassAnnotationVerify;
    }

    @Override
    public ClassAnnotationEnum getFailValue() {
        return failClassAnnotation;
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
