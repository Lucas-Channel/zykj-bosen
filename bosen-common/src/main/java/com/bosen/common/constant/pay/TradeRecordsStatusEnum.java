package com.bosen.common.constant.pay;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 交易状态
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/1
 */
public enum TradeRecordsStatusEnum {
    /**
     * 提现中
     */
    In_Cashing(1, "提现中"),

    /**
     * 提现成功
     */
    Cash_Out_Success(2, "提现成功"),

    /**
     * 提现失败
     */
    Cash_Out_Fail(3, "提现失败"),

    /**
     * 支付中
     */
    Paying(4, "支付中"),
    /**
     * 支付失败
     */
    Pay_Fail(5, "支付失败"),
    /**
     * 支付成功
     */
    Pay_Success(6, "支付成功"),
    /**
     * 退款中
     */
    Refunding(7, "退款中"),
    /**
     * 退款成功
     */
    Refund_Success(8, "退款成功"),
    /**
     * 退款失败
     */
    Refund_Fail(9, "退款失败"),

    ;

    private final Integer code;
    private final String message;

    private static Map<Integer,String> messageMap = Arrays.stream(TradeRecordsStatusEnum.values()).collect(Collectors.toMap(TradeRecordsStatusEnum::getCode,TradeRecordsStatusEnum::getMessage));

    public static String getMessageByCode(Integer code) {
        return messageMap.get(code);
    }

    TradeRecordsStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
