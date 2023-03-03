package com.bosen.common.exception;


import com.bosen.common.constant.response.ResponseCode;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * 自定义业务异常类，用于全局异常处理
 */
public class BusinessException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 7176464042595223410L;

    public BusinessException(ResponseCode code) {
        super(Arrays.stream(ResponseCode.values()).filter(c -> Objects.equals(c, code)).findFirst().orElse(ResponseCode.BUSINESS_ERROR).getMessage());
        this.code = code;
        this.msg = super.getMessage();
    }

    public BusinessException(ResponseCode code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String msg) {
        super(msg);
        this.code = ResponseCode.BUSINESS_ERROR;
        this.msg = msg;
    }

    public BusinessException(int errorCode, String msg) {
        super(msg);
        this.errorCode=errorCode;
        this.msg=msg;
    }

    public static void isThrow(boolean b, ResponseCode code) {
        if (b) {
            throw new BusinessException(code);
        }
    }

    private ResponseCode code;
    private int errorCode;
    private String msg;

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
