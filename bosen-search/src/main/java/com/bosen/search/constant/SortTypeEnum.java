package com.bosen.search.constant;

import java.util.Arrays;

/**
 * 排序方式
 * @author Lucas
 */
public enum SortTypeEnum {
    SoldDown(1, "销量从高到低"),
    PriceDown(2, "价格从高到低"),
    PriceUp(3, "价格从低到高")
    ;

    private final Integer code;
    private final String message;

    SortTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static SortTypeEnum parse(Integer code) {
        return Arrays.stream(SortTypeEnum.values()).filter(e -> e.getCode().equals(code)).findFirst().orElse(null);
    }
}
