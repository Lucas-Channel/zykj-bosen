package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.constant.common.YesOrNoConstant;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/19
 */
@Data
@Accessors(chain = true)
@TableName("bs_freight_space")
public class FreightSpaceDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = -3976314454632654284L;

    /**
     * 仓位名称
     */
    private String name;

    /**
     * 仓位编码
     */
    private String code;

    /**
     * 是否正常使用
     * @see YesOrNoConstant
     */
    private Integer status;

    /**
     * 说明
     */
    private String remark;


    /**
     * 商品所属商家
     */
    private String merchantId;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 商品所属商家角色id
     */
    private String merchantRoleId;
}
