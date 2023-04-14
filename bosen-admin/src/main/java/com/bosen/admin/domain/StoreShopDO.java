package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.admin.constant.StoreOpenCloseStatusEnum;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * 店铺与商城关系表
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
@Data
@TableName("/bs_store_shop")
public class StoreShopDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = -6039670216460894120L;
    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 商城id
     */
    private String shopId;

    /**
     * 开店状态
     * @see StoreOpenCloseStatusEnum
     */
    private Integer status;
}
