package com.monster.commons.generate.service.impl;


import com.monster.commons.generate.service.ConfigureInfoService;

/**
 * @author CustomLZH
 * @version 1.0
 * @date 2022/9/28 0:44
 * @since JDK1.8
 */
public class ConfigureInfoServiceImpl implements ConfigureInfoService {
    @Override
    public Boolean getLombok() {
        return true;
    }

    @Override
    public Boolean getMyBatisPlusTableName() {
        return true;
    }

    @Override
    public Boolean getColumnNamePrefix() {
        return true;
    }

    @Override
    public String getColumnNamePrefixValue() {
        return "i_,s_,d_";
    }
}
