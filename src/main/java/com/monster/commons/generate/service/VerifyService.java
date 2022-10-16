package com.monster.commons.generate.service;


import com.monster.commons.generate.enums.VerifyEnum;

/**
 * @author CustomLZH
 * @version 1.0
 * @date 2022/10/16 15:17
 * @since JDK1.8
 */
public interface VerifyService<T extends VerifyService<T, E>, E extends TargetService<?>> {


    /**
     * 获取验证类型
     *
     * @return
     */
    VerifyEnum getVerifyValue();

    /**
     * 获取验证成功的引用
     *
     * @return
     */
    T getSucceedReference();

    /**
     * 获取验证成功的值
     *
     * @return
     */
    E getSucceedValue();

    /**
     * 获取验证失败的引用
     *
     * @return
     */
    T getFailReference();

    /**
     * 获取验证失败的值
     *
     * @return
     */
    E getFailValue();

    /**
     * 获取验证的类型
     *
     * @return
     */
    String getVerifyClass();

    /**
     * 获取验证字符串的值
     *
     * @return
     */
    String getStringVerifyValue();
}
