package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.admin.constant.StoreStatusEnum;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 店铺
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/13
 */
@Data
@Accessors(chain = true)
@TableName("bs_store")
public class StoreDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = -1698675323076867765L;

    /**
     * 商家id
     */
    private String merchantId;

    /**
     * 商家角色id
     */
    private String merchantRoleId;

    /**
     * 店铺名称
     */
    private String name;

    /**
     * 店铺logo
     */
    private String logoUrl;

    /**
     * 描述
     */
    private String remark;

    /**
     * 状态
     * @see StoreStatusEnum
     */
    private Integer status;

    /**
     * 信誉分
     */
    private BigDecimal creditScore;
}
