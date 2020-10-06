package com.common.api;

/**
 * @Description: 封装API的错误码
 * @auther: xrq
 * @date: 2020/9/10 20:46
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
