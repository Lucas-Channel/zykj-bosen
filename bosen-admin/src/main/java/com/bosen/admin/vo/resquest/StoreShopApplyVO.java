package com.bosen.admin.vo.resquest;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 开店申请
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
@Data
public class StoreShopApplyVO implements Serializable {
    private static final long serialVersionUID = 6934637173659777686L;
    /**
     * 店铺id
     */
    @NotEmpty(message = "店铺id不能为空")
    private String storeId;

    /**
     * 商城id
     */
    @NotEmpty(message = "商城id不能为空")
    private String shopId;
}
