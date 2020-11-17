package com.chenchen.base.controller.exceptionhandler;

import com.chenchen.common.entity.ResultEntity;
import com.chenchen.common.entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 自定义异常处理类
 * @author chenchen
 */
@RestControllerAdvice
public class BaseExceptionHandler {

    /**
     * 自定义异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResultEntity exception(Exception exception) {
        exception.printStackTrace();
        return new ResultEntity(StatusCode.ERROR, false, exception.getMessage());
    }
}
