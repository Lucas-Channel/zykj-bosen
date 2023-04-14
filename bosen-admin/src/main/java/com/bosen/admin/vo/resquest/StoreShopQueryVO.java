package com.bosen.admin.vo.resquest;

import com.bosen.common.domain.PageVO;
import lombok.Data;

/**
 * 查询条件
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
@Data
public class StoreShopQueryVO extends PageVO {
    /**
     * 店铺名称
     */
    private String name;

    /**
     * 开店状态
     */
    private Integer status;

    /**
     * 商城id
     */
    private String shopId;

}
