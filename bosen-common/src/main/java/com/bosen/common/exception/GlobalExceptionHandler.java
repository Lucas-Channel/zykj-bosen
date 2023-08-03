package com.bosen.common.exception;

import com.bosen.common.constant.response.ResponseData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ResponseData<Void> handleBusinessException(BusinessException e) {
        e.printStackTrace();
        if (e.getCode() != null) {
            return ResponseData.fail(e.getCode().getCode(), e.getMessage());
        }
        return ResponseData.fail(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseData<Void> handleException(Exception e) {
        e.printStackTrace();
        return ResponseData.fail(e.getCause().getMessage());
    }

}
