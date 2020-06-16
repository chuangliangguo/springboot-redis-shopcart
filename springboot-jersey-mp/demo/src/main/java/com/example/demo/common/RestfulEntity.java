package com.example.demo.common;

import com.example.demo.exception.ApiErrorCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口响应
 */
@Data
@NoArgsConstructor
public class RestfulEntity<T> {

    /**
     * 接口调用返回状态，TRUE为正常返回，FALSE为异常返回
     */
    @ApiModelProperty(value = "返回状态", required = true, notes = "true or false")
    private Boolean status;
    /**
     * 接口调用返回编码，暂未使用，默认为200(正常)，
     * 当调用出错时，可自行填写对应错误码
     */
    @ApiModelProperty(value = "接口调用返回码", required = true)
    private String code;
    /**
     * 接口调用返回数据
     */
    @ApiModelProperty(value = "返回数据", required = true)
    private T res;
    /**
     * 调用成功或者失败都可以设置消息
     */
    @ApiModelProperty(value = "接口调用信息", required = true)
    private String msg;

    public RestfulEntity(Boolean status, String code, T res) {
        this.status = status;
        this.code = code;
        this.res = res;
    }

    public RestfulEntity(Boolean status, String code, T res, String msg) {
        this.status = status;
        this.code = code;
        this.res = res;
        this.msg = msg;
    }

    public RestfulEntity(Boolean status, ApiErrorCode code, T res) {
        this.status = status;
        this.code = String.valueOf(code.getCode());
        this.msg = code.getMessage();
        this.res = res;
    }


    /**
     * 返回异常请求时调用，默认res为null
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T> RestfulEntity<T> getFailure(ApiErrorCode errorCode) {
        return new RestfulEntity<>(false,  errorCode, null);
    }

    /**
     * 返回异常请求时调用，res可自定义
     * @param errorCode
     * @param res
     * @param <T>
     * @return
     */
    public static <T> RestfulEntity<T> getFailure(ApiErrorCode errorCode, T res) {
        return new RestfulEntity<>(false,  errorCode, res);
    }

    /**
     * 返回异常请求时调用，res可自定义，覆盖原有返回信息
     * @param errorCode
     * @param res
     * @param <T>
     * @return
     */
    public static <T> RestfulEntity<T> getFailure(ApiErrorCode errorCode, T res, String message) {
        return new RestfulEntity<T>(false,  String.valueOf(errorCode.getCode()), res, message);
    }

    /**
     * 返回正常请求时调用
     * @param res
     * @return
     */
    public static <T> RestfulEntity<T> getSuccess(T res) {
        return new RestfulEntity<>(true, "200", res,"Successful");
    }

    /**
     * 返回正常请求时调用
     * 自定义成功消息
     * @param res
     * @param <T>
     * @return
     */
    public static <T> RestfulEntity<T> getSuccess(T res, String msg) {
        return new RestfulEntity<>(true, "200", res, msg);
    }
}

