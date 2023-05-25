package com.bosen.common.constant.response;

import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 统一返回编码枚举
 */
public enum ResponseCode {

    SUCCESS(200, "操作成功"),

    FAILED(-1, "操作失败"),

    FORBIDDEN(403, "没有相关权限"),

    TOKEN_DOES_NOT_EXIST_IN_CACHE(1101, "登录已过期，请重新登录"),

    REQUEST_PARAM_CHECK_FAILED(1102, "参数校验或转换错误"),

    REQUEST_METHOD_NOT_SUPPORTED(1103, "不支持的Http请求方法"),

    REQUEST_CONTENT_TYPE_NOT_SUPPORTED(1104, "不支持的Http Content-Type设置"),

    REQUEST_BODY_IS_MISSING(1105, "请求体参数解析异常"),

    USER_ACCOUNT_OR_PASSWORD_INCORRECT(1106, "用户账号或密码错误"),

    USER_SMS_CODE_EXPIRED(1107, "验证码不存在或已过期，请重新获取"),

    USER_SMS_CODE_INCORRECT(1108, "验证码错误，请重新输入"),

    USER_ACCOUNT_HAS_BEEN_FROZEN(1109, "账号已被冻结"),

    LOGIN_AUTH_CODE_EXPIRED(1200, "授权码已过期"),

    FILE_DOWNLOAD_ERROR(1201, "文件下载错误"),

    FILE_UPLOAD_ERROR(1202, "文件上传错误"),

    UPLOADED_FILE_FORMAT_ERROR(1203, "上传文件为空或格式错误"),

    UPLOADED_FILE_SIZE_EXCEPTION(1204, "上传文件大小超过最大限制"),

    FEIGN_SERVICE_ERROR(1205, "OpenFeign 服务异常."),

    SERVICE_ORDER_ERROR(1206, "请求订单服务失败"),

    BUSINESS_ERROR(1207, "服务内部错误"),

    ADMIN_LOGIN_ERROR(1208, "后台登录服务异常"),

    CLIENT_AUTHENTICATION_FAILED(1209, "客户端认证失败"),

    UNAUTHORIZED(1210, "暂未登录或token已经过期"),

    ADMIN_SERVICE_ERROR(1211, "后台管理服务异常"),

    UPDATE_ROLE_MENU_PERMISSION_ERROR(1212, "更新系统角色的菜单权限失败"),

    USER_NAME_HAS_EXIT_ERROR(1213, "当前账号已存在"),

    USER_NAME_NOT_EXIT_ERROR(1214, "当前账号不存在"),

    OLD_PASSWORD_ERROR(1215, "输入的旧密码与原密码不匹配，请重新输入"),

    DELETE_OLD_ROLES_ERROR(1216, "删除历史角色失败"),

    HAS_EXIT_DEFAULT_MEMBER_LEVEL_ERROR(1217, "已存在默认会员等级"),

    USER_NAME_OR_PHONE_HAS_EXIT_ERROR(1218, "当前账号名称/手机号已存在"),

    ONLY_UPDATE_MEMBER_SELF_INFO_ERROR(1219, "只能更新自己的会员信息"),

    SUBMIT_APPROVE_PRODUCT_STATUS_ERROR(1220, "只有待提交审核的商品才可以提交审核"),

    SUBMIT_APPROVE_PRODUCT_BY_SELF_ERROR(1221, "只有商家自身才可以提交审核"),

    CANNOT_DELETE_PRODUCT_ERROR(1222, "待审核，已上架，审核通过的商品不能删除"),

    APPROVE_PRODUCT_STATUS_ERROR(1223, "只有待审核商品才能进行审核"),

    STORE_NOT_EXIT_ERROR(1224, "店铺不存在"),

    WAIT_APPROVE_OPEN_STORE_APPLY_ERROR(1225, "只能审核待审核开店申请的店铺"),

    STORE_HAS_SHOP_ERROR(1226, "该商城下存在改店铺"),

    STORE_SHOP_CANNOT_CLOSE_ERROR(1227, "该店铺不满足关闭条件"),

    WAIT_APPROVE_CLOSE_STORE_APPLY_ERROR(1228, "只能审核待审核关店申请的店铺"),

    SAVE_PRODUCT_AREA_LIST_NOT_EMPTY_ERROR(1229, "销售区域不能为空"),

    PRODUCT_NOT_EXIT_ERROR(1230, "商品不存在"),

    FREIGHT_SPACE_NOT_EXIT_ERROR(1231, "仓位不存在"),

    SKU_NOT_EXIT_ERROR(1232, "SKU不存在"),

    SKU_HAS_RACKING_ERROR(1233, "该商品已上架，不能修改库存，如需修改，请下架后操作"),

    RACKING_COUNT_ERROR(1234, "上架数量不匹配。请确认商品是否是自身商品以及商品是否审核通过或已下架或是否建立仓位库存"),

    SAVE_PRODUCT_STORE_SHOP_ERROR(1235, "保存商品上架记录失败"),

    UPDATE_PRODUCT_ERROR(1236, "更新商品信息失败"),

    DELETE_PRODUCT_UPPER_RECORD_HISTORY_ERROR(1237, "删除商品历史上架记录失败"),

    SEARCH_SERVICE_ERROR(1238, "搜索服务异常"),

    RACKING_PRODUCT_ERROR(1239, "商品上架失败"),

    UPDATE_PRODUCT_SKU_SALES_COUNT_ERROR(1240, "更新商品sku已售数量失败"),

    DOWN_PRODUCT_ERROR(1241, "商品下架失败"),

    DOWN_PRODUCT_ONLY_UP_ERROR(1242, "只能下架商品状态为已上架的商品"),

    RACKING_PRODUCT_BRAND_ERROR(1243, "品牌上架失败"),

    DOWN_PRODUCT_BRAND_ERROR(1244, "品牌下架失败"),
    ;


    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static String getMessage(int code) {
        ResponseCode responseCode = Arrays.stream(ResponseCode.values()).filter(r -> r.getCode() == code).findFirst().orElse(null);
        if (responseCode != null) {
            return responseCode.getMessage();
        }
        return null;
    }

    public static String getMessage(int code, String msg) {
        ResponseCode responseCode = Arrays.stream(ResponseCode.values()).filter(r -> r.getCode() == code).findFirst().orElse(null);
        if (responseCode != null) {
            if (responseCode.getMessage().contains("%s") && !responseCode.getMessage().equals(msg)) {
                return String.format(responseCode.getMessage(), msg);
            }
            return StrUtil.isNotBlank(msg) ? msg : responseCode.getMessage();
        }
        return null;
    }

    public static ResponseCode getByCode(int code) {
        return Arrays.stream(ResponseCode.values()).filter(r -> r.getCode() == code).findFirst().orElse(BUSINESS_ERROR);
    }

    /**
     * 将所有枚举转换成map<Integer,String>
     */
    public static List<String> getAllToMap() {
        // List<Map<String, String>> list = new ArrayList<>();
        List<String> list = new ArrayList<>();
        // Map<String, String> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        //计数器
        int count = 0;
        List<ResponseCode> responseCodeList = Arrays.stream(ResponseCode.values()).sorted(Comparator.comparingInt(ResponseCode::getCode)).collect(Collectors.toList());
        System.out.println("总条数：" + responseCodeList.size());
        for (ResponseCode responseCode : responseCodeList) {
            if (count <= 2000) {
                // map.put(responseCode.getCode()+"", responseCode.getMessage());
                sb.append(responseCode.getCode()).append("= ").append(responseCode.getMessage()).append(" ; ");
                count += responseCode.getMessage().length();
            } else {
                list.add(sb.substring(0, sb.length() - 1));
                //  map = new HashMap<>();
                sb = new StringBuilder();
                sb.append(responseCode.getCode()).append("= ").append(responseCode.getMessage()).append(" ; ");

                //map.put(responseCode.getCode()+"", responseCode.getMessage());
                count = 0;
            }
        }
        //添加剩下的数据
        list.add(sb.substring(0, sb.length() - 1));
        return list;
    }
}
