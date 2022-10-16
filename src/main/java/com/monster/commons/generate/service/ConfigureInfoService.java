package com.monster.commons.generate.service;

/**
 * 配置信息
 * @author CustomLZH
 * @version 1.0
 * @date 2022/9/28 0:32
 * @since JDK1.8
 */
public interface ConfigureInfoService {

    /**
     * 是否启用Lombok
     * @return true 是，false 否
     */
    Boolean getLombok();

    /**
     * 是否启用MyBatisPlus表名注解
     * @return true 是，false 否
     */
    Boolean getMyBatisPlusTableName();

    /**
     * 是否启用列名称前缀
     * @return true 是，false 否
     */
    Boolean getColumnNamePrefix();

    /**
     * 获取前缀值
     * @return 前缀值，多个用,分隔
     */
    String getColumnNamePrefixValue();

}
