package com.bosen.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * 平台后台-会员支付策略
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/20
 */
@Data
@TableName("bs_member_payment_strategy")
public class MemberPaymentStrategyDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = 2216388430206862381L;

    /**
     * 支付策略名称
     */
    private String strategyName;

    /**
     * 状态，0-停用，1-启用
     */
    private Integer status;
}
