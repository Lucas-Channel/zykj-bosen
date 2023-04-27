package com.bosen.template.adorn.constant;


import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 模板类型
 * @author Lucas
 * @version 2.0.0
 */
public enum TemplateTypeEnum {

    PLATFORM(0,"平台模板"),

    ENTERPRISE(1,"企业商城模板"),

    SHOP_STORE(2, "店铺模板"),

    ACTIVITY(3, "活动模板"),
    ;

    TemplateTypeEnum(Integer code, String message) {
        this.code = code;
    }

    private Integer code;
    private String message;

    private static Map<Integer,String> messageMap = Arrays.stream(TemplateTypeEnum.values()).collect(Collectors.toMap(TemplateTypeEnum::getCode,TemplateTypeEnum::getMessage));

    public static String getMessageByCode(Integer code) {
        return messageMap.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
