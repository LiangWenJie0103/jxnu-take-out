package com.lwj.handler;

import com.lwj.exception.BaseException;
import com.lwj.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BaseException.class)
    public Result exceptionHandler(BaseException e) {
        log.error("异常信息：{}", e.getMessage());
        return Result.error(e.getMessage());
    }

    /**
     * 兜底处理
    */
    @ExceptionHandler(Exception.class)
    public Result handleOtherException(Exception e) {
        log.error("系统异常：", e);
        return Result.error("系统错误，请联系管理员");
    }
}
