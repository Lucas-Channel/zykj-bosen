package com.bosen.system.constants;

import java.util.Arrays;

/**
 * 资金收款方向
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/20
 */
public enum FundModelEnums {
    /**
     * 平台代收模式 - 1
     */
    PLATFORM_EXCHANGE(1, "平台代收"),

    /**
     * 商家直接到账模式 - 2
     */
    DIRECT_TO_ACCOUNT(2, "商家直接到账");

    private final Integer code;

    private final String name;

    FundModelEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getNameByCode(Integer code) {
        return Arrays.stream(FundModelEnums.values()).filter(v -> v.getCode().equals(code)).map(FundModelEnums::getName).findFirst().orElse("");
    }
}
