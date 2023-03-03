package com.bosen.pay.vo.response;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/28
 */
@Data
public class MemberAssetAccountDetail implements Serializable {
    private static final long serialVersionUID = -3367434481582679030L;

    /**
     * 会员id
     */
    @NotNull
    private Long memberId;

    private Long id;

    /**
     * 账户余额
     */
    private BigDecimal accountBalance;

    /**
     * 锁定金额
     */
    private BigDecimal lockBalance;

    /**
     * 账户状态：1-正常, 2-已冻结
     */
    @NotNull
    private Integer accountStatus;

    /**
     * 支付密码
     */
    private String payPassword;

    /**
     * 更新支付密码标志, 默认0
     */
    private Integer updatePasswordFlag;
}
