package com.monster.commons.generate.enums;

import lombok.Getter;

/**
 * @author LiuZhaoHong
 * @version 1.0
 * @date 2022/10/15 20:15
 * @since JDK1.8
 */
@Getter
public enum ImportVerifyEnum {


    /**
     * MyBatis-Plus
     */
    MY_BATIS_PLUS_ID_TYPE(VerifyEnum.MYBATIS_PLUS_TABLE_NAME, null,
            ImportEnum.MY_BATIS_PLUS_ID_TYPE, null, null,
            "Boolean", null),

    MY_BATIS_PLUS_TABLE_ID(VerifyEnum.MYBATIS_PLUS_TABLE_NAME, null,
            ImportEnum.MY_BATIS_PLUS_TABLE_ID, null, null,
            "Boolean", null),

    MY_BATIS_PLUS_TABLE_FIELD(VerifyEnum.MYBATIS_PLUS_TABLE_NAME, null,
            ImportEnum.MY_BATIS_PLUS_TABLE_FIELD, null, null,
            "Boolean", null),

    MY_BATIS_PLUS_TABLE_NAME(VerifyEnum.MYBATIS_PLUS_TABLE_NAME, null,
            ImportEnum.MY_BATIS_PLUS_TABLE_NAME, null, null,
            "Boolean", null),


    /**
     * Lombok-Data
     */
    LOMBOK_DATA(VerifyEnum.LOMBOK_DATA, null, ImportEnum.LOMBOK_DATA,
            null, null, "Boolean", null),

    /**
     * 日期类型
     */
    DATE_TYPE_IMPORT(VerifyEnum.DATE_TYPE, null, ImportEnum.DATE_TYPE_IMPORT,
            null, null, "Boolean", null),


    /**
     * 序列化
     */
    SERIALIZABLE_IMPORT(VerifyEnum.SERIALIZABLE,  null, ImportEnum.SERIALIZABLE_IMPORT,
            null, null, "Boolean", null),

    ;

    /**
     * 判断参数
     */
    private VerifyEnum verifyValue;

    /**
     * 成功的判断
     */
    private ImportVerifyEnum succeedImportVerify;

    /**
     * 成功的引用
     */
    private ImportEnum succeedImport;

    /**
     * 失败的判断
     */
    private ImportVerifyEnum failImportVerify;

    /**
     * 失败的引用
     */
    private ImportEnum failImport;

    /**
     * 判断类型
     */
    private String verifyClass;

    /**
     * 判断类型为String的值
     */
    private String stringVerifyValue;

    ImportVerifyEnum(VerifyEnum verifyValue,
                     ImportVerifyEnum succeedImportVerify,
                     ImportEnum succeedImport,
                     ImportVerifyEnum failImportVerify,
                     ImportEnum failImport,
                     String verifyClass,
                     String stringVerifyValue) {

        this.verifyValue = verifyValue;
        this.succeedImportVerify = succeedImportVerify;
        this.succeedImport = succeedImport;
        this.failImportVerify = failImportVerify;
        this.failImport = failImport;
        this.verifyClass = verifyClass;
        this.stringVerifyValue = stringVerifyValue;
    }
}
