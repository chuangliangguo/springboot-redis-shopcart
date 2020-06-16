package com.example.demo.exception;

import com.example.demo.common.RestfulEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description: 统一错误拦截
 */

@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 统一拦截调用api异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ApiException.class)
    public RestfulEntity<Object> apiException(ApiException e) {
        log.error("handle apiException error:",  e);
        return RestfulEntity.getFailure(e.getApiErrorCode());
    }

    /**
     * 统一拦截其他异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public RestfulEntity <Object> handleDefault(Exception e) {
        log.error("handle default error:", e);
        return RestfulEntity.getFailure(ApiErrorCode.SYSTEM_ERROR);
    }
}
