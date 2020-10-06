package com.common.exception;

import com.common.api.IErrorCode;

/**
 * @Description: 断言处理类，用于抛出各种API异常
 * @auther: xrq
 * @date: 2020/9/10 20:49
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
