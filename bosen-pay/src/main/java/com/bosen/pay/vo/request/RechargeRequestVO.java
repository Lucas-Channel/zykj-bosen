package com.bosen.pay.vo.request;

import com.bosen.common.constant.pay.PayChannelEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 资金账户充值实体
 */
@Data
public class RechargeRequestVO {
    /**
     * 会员资金账户id
     */
    @NotNull(message = "会员资金账户id不能为空")
    private Long memberAssetAccountId;

    /**
     * 会员id
     */
    @NotNull(message = "会员id不能为空")
    private Long memberId;

    /**
     * 充值金额
     */
    @NotNull(message = "充值金额不能为空")
    private BigDecimal money;

    /**
     * 充值方式
     * @see PayChannelEnum
     */
    @NotNull(message = "充值方式不能为空")
    private Integer type;

    /**
     * 窗口：1，PC，2，app
     */
    @NotNull(message = "窗口不能为空")
    private Integer portType;
}
