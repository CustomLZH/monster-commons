package com.monster.commons.beans;

import com.monster.commons.enums.ResponseEnum;
import com.monster.commons.service.ResponseService;

/**
 * 公共返回参数
 *
 * @author LiuZhaoHong
 * @version 1.0
 * @date 2023/12/31
 * @since JDK1.8
 */
public class ResponseData<T> {

    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public ResponseData(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseData(ResponseService impl) {
        this.code = impl.getCode();
        this.message = impl.getMessage();
    }

    public ResponseData(ResponseService impl, T data) {
        this.code = impl.getCode();
        this.message = impl.getMessage();
        this.data = data;
    }

    /**
     * 成功无参数
     * @return 返回默认参数
     */
    public static ResponseData<Void> succeed() {
        return new ResponseData<>(ResponseEnum.succeed);
    }

    /**
     * 成功数据参数
     * @return 返回默认参数
     */
    public static <T> ResponseData<T> succeed(T data) {
        return new ResponseData<>(ResponseEnum.succeed, data);
    }

    /**
     * 失败无参数
     * @return 返回默认参数
     */
    public static ResponseData<Void> fail() {
        return new ResponseData<>(ResponseEnum.fail);
    }

    /**
     * 失败数据参数
     * @return 返回默认参数
     */
    public static <T> ResponseData<T> fail(T data) {
        return new ResponseData<>(ResponseEnum.fail, data);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
