package com.bosen.admin.vo.response;

import com.bosen.admin.constant.StoreStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 店铺明细
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
@Data
public class StoreDetailVO implements Serializable {
    private static final long serialVersionUID = -1071355764910765800L;
    private String id;

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
    private String describe;

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
