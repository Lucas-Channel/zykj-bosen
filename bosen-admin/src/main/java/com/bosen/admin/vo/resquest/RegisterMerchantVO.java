package com.bosen.admin.vo.resquest;

import com.bosen.common.constant.common.UserStatusConstant;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商户注册参数
 */
@Data
public class RegisterMerchantVO {

    /**
     * 商户名称
     */
    @NotBlank
    private String merchantName;

    /**
     * 商家注册手机号
     */
    @NotBlank
    private String registerMobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 平台商家等级
     */
    @NotNull
    private Long adminMerchantLevelId;

    /**
     * 商家收件邮箱
     */
    private String merchantEmail;

    /**
     * 商家联系人手机号
     */
    private String relationMobile;

    /**
     * 商家联系人
     */
    private String relationName;

    /**
     * 联系人收件箱
     */
    private String relationEmail;

    /**
     * 客服电话
     */
    private String serviceMobile;

    /**
     * 下单通知短信接收电话
     */
    @NotBlank
    private String orderNoticeMobile;

    private String icon;

    /**
     * 状态
     * @see UserStatusConstant
     */
    private Integer status;

    /**
     * 最后登录日期
     */
    private LocalDateTime loginTime;

    /**
     * 当前登陆状态
     */
    private Integer loginFlag;

    /**
     * 是否更新密码
     */
    private Integer hasUpdatePassword;

    /**
     * 是否平台旗下自营商家， 0，否，1，是
     */
    private Integer notPlatformMerchant;

    /**
     * 平台抽佣比例，非平台旗下自营商家时，必填，平台审核时填写
     */
    private BigDecimal platformPumpingScale;

    /**
     * 营业信息
     */
    @NotNull
    private MerchantBusinessLicenseVO merchantBusinessLicenseVO;
}
