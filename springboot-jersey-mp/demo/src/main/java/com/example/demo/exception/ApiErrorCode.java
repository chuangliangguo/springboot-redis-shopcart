package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public enum ApiErrorCode {

    SYSTEM_ERROR(500, "系统异常"),
    PAGE_SIZE_ERROR(11003,"查询条数不在范围内"),
    PAGE_NUM_ERROR(11004,"页数不在范围内");

    //错误码
    private final Integer code;
    //错误消息
    private final String message;
//    //http状态
//    private final int status;

    ApiErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
