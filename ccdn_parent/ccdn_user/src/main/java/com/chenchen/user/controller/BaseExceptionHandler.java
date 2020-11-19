package com.chenchen.user.controller;
import com.chenchen.common.entity.ResultEntity;
import com.chenchen.common.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 自定义异常类
 * @author chenchen
 */
@ControllerAdvice
public class BaseExceptionHandler {
	
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultEntity error(Exception e){
        e.printStackTrace();        
        return new ResultEntity(StatusCode.ERROR,false, e.getMessage());
    }
}
