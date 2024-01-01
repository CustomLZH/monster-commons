package com.monster.commons.enums;

import com.monster.commons.service.ResponseService;

/**
 * 该文件描述
 *
 * @author LiuZhaoHong
 * @version 1.0
 * @date 2023/12/31
 * @since JDK1.8
 */
public enum ResponseEnum implements ResponseService {

    /**
     * 成功
     */
    succeed("200","请求成功"),
    /**
     * 失败
     */
    fail("500","请求失败"),

    ;
    /**
     * 状态码
     */
    private String code;

    /**
     * 状态消息
     */
    private String message;

    ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
