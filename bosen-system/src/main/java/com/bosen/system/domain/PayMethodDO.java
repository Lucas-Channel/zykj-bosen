package com.bosen.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * 支付方式
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/6
 */
@Data
@TableName("bs_pay_method")
public class PayMethodDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = 4916153864619189942L;

    private String payMethodCode;

    private String payMethodName;

    private String iconUrl;

    private Integer enableFlag;

}
