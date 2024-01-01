package com.monster.commons.service;

/**
 * 公共返回参数service
 *
 * @author LiuZhaoHong
 * @version 1.0
 * @date 2023/12/31
 * @since JDK1.8
 */
public interface ResponseService {

    /**
     * 获取状态码
     * @return
     */
    String getCode();

    /**
     * 获取消息
     * @return
     */
    String getMessage();



}
