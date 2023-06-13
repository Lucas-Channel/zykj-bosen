package com.bosen.common.constant.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回数据模型
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/22
 */
@Data
public class ResponseData<T> implements Serializable {
    private static final long serialVersionUID = -7203711070403319547L;

    private int code;

    private String msg;

    private T data;

    public ResponseData() {
    }

    public ResponseData(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseData<T> success() {
        ResponseCode responseCode = ResponseCode.SUCCESS;
        return new ResponseData<>(responseCode.getCode(), responseCode.getMessage(), null);
    }

    public static <T> ResponseData<T> success(T data) {
        ResponseCode responseCode = ResponseCode.SUCCESS;
        return new ResponseData<>(responseCode.getCode(), responseCode.getMessage(), data);
    }

    public static <T> ResponseData<T> fail(ResponseCode responseCode) {
        return new ResponseData<>(responseCode.getCode(), responseCode.getMessage(), null);
    }

    public static <T> ResponseData<T> fail(String errorMessage) {
        return new ResponseData<>(ResponseCode.BUSINESS_ERROR.getCode(), errorMessage, null);
    }

    public static <T> ResponseData<T> fail(int code, String errorMessage) {
        return new ResponseData<>(code, errorMessage, null);
    }

    public static <T> ResponseData<T> fail() {
        return new ResponseData(ResponseCode.FAILED.getCode(), ResponseCode.FAILED.getMessage(), null);
    }

    public static <T> ResponseData<T> judge(boolean status) {
        if (status) {
            return success();
        } else {
            return fail();
        }
    }

    /**
     * 未登录返回结果
     */
    public static <T> ResponseData<T> unauthorized(T data) {
        return new ResponseData<T>(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> ResponseData<T> forbidden(T data) {
        return new ResponseData<T>(ResponseCode.FORBIDDEN.getCode(), ResponseCode.FORBIDDEN.getMessage(), data);
    }
}
