package com.bosen.product.constant;


import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 库存事务类型
 */
public enum InventoryTransactionTypeConstants {
    STOCK_IN("STOCK_IN", "入库"),
    STOCK_OUT("STOCK_OUT", "出库"),
    TRANSFER_IN("TRANSFER_IN", "转入"),
    TRANSFER_OUT("TRANSFER_OUT", "转出"),
    RETURN("RETURN", "退货"),
    DELIVERY("DELIVERY", "发货"),
    ;

    InventoryTransactionTypeConstants(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private final String code;
    private final String message;

    private static final Map<String,String> messageMap = Arrays.stream(InventoryTransactionTypeConstants.values()).collect(Collectors.toMap(InventoryTransactionTypeConstants::getCode, InventoryTransactionTypeConstants::getMessage));

    public static String getMessageByCode(String code) {
        return messageMap.get(code);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
