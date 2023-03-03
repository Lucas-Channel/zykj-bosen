package com.bosen.common.exception;

import com.bosen.common.constant.response.ResponseData;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ResponseData<Void> handleBusinessException(BusinessException e) {
        if (e.getCode() != null) {
            return ResponseData.fail(e.getCode().getCode(), e.getCode().getMessage());
        }
        return ResponseData.fail(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseData<Void> handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return ResponseData.fail(message);
    }

    @ExceptionHandler(value = BindException.class)
    public ResponseData<Void>handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return ResponseData.fail(message);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseData<Void> handleException(Exception e) {
        return ResponseData.fail(e.getMessage());
    }

}
