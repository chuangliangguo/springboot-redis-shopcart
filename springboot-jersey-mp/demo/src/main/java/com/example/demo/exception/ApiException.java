package com.example.demo.exception;

import lombok.Data;


/**
 * 业务异常，封装ApiErrorCode和其他错误信息res
 * @see com.example.demo.exception.ApiErrorCode
 */
@Data
public class ApiException extends RuntimeException{
    /**
     * 异常响应码、响应信息
     */
    private ApiErrorCode apiErrorCode;
    /**
     * 其他返回信息
     */
    private Object res;

    public ApiException(ApiErrorCode apiErrorCode) {
        super(apiErrorCode.getMessage());
        this.apiErrorCode = apiErrorCode;
    }

    public ApiException(ApiErrorCode apiErrorCode,Object res) {
        super(apiErrorCode.getMessage());
        this.apiErrorCode = apiErrorCode;
        this.res=res;
    }
}
