package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.constant.common.UserStatusConstant;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 平台入驻商家信息
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_merchant")
public class MerchantDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = -6493729386040690106L;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 商家注册手机号
     */
    private String registerMobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 平台商家等级
     */
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
}
