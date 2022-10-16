package com.monster.commons.generate.service;

/**
 * @author CustomLZH
 * @version 1.0
 * @date 2022/10/16 15:27
 * @since JDK1.8
 */
public interface TargetService<T> {

    /**
     * 获取注解格式化值
     * @return
     */
    String getFormat();

    /**
     * 获取表值
     * @return
     */
    T getType();
}
