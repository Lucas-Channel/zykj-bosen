package com.bosen.pay.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.constant.pay.PayDealType;
import com.bosen.common.constant.pay.TradeRecordsStatusEnum;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 资金账户交易记录
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/1
 */
@Data
@TableName("bs_pay_account_trade_record")
public class AccountTradeRecordsDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 3146907230493638013L;

    /**
     * bs交易单号
     */
    private String tradeCode;

    /**
     * 交易时间
     */
    private LocalDateTime tradeTime;

    /**
     * 交易金额
     */
    private BigDecimal tradeMoney;

    /**
     * 交易项目
     * @see PayDealType
     */
    private Integer operation;

    /**
     * 状态
     * @see TradeRecordsStatusEnum
     */
    private Integer status;

    /**
     * 银行账户名称(提现)
     */
    private String bankAccountName;

    /**
     * 银行账号(提现)
     */
    private String bankAccount;

    /**
     * 开户银行(提现)
     */
    private String bankName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 支付平台交易号
     */
    private String transId;

    /**
     * 业务单号
     */
    private String businessCode;
}
