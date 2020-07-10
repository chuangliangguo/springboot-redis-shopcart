package com.spacemall.core.exception;

public enum ApiErrorCode {

    SYSTEM_ERROR(500, "系统异常"),

    //购物车定义错误码
    REPEAT_ADDITION_PRODUCT_ERROR(11020,"请勿重复添加产品"),
    NO_PRODUCT_ERROR(11021,"删除产品失败");

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
