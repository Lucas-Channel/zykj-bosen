package com.bosen.pay.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 会员资金账户表
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/28
 */
@Data
@TableName("bs_member_asset_account")
public class MemberAssetAccountDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 1608661489110453821L;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 账户余额
     */
    private BigDecimal accountBalance;

    /**
     * 余额加密密文
     */
    private String accountBalanceEncrypt;

    /**
     * 锁定金额
     */
    private BigDecimal lockBalance;

    /**
     * 锁定金额加密密文
     */
    private String lockBalanceEncrypt;

    /**
     * 账户状态：1-正常, 2-已冻结
     */
    private Integer accountStatus;

    /**
     * 支付密码
     */
    private String payPassword;

    /**
     * 更新支付密码标志
     */
    private Integer updatePasswordFlag;

    /**
     * 是否开启免密支付：1开启，0未开启
     */
    private Integer noSecretPay;
}
