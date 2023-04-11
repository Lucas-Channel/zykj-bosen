package com.bosen.product.vo.request;

import com.bosen.common.domain.PageVO;
import lombok.Data;

/**
 *  商品分页查询对象
 */
@Data
public class ProductQueryVO extends PageVO {

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 平台品类
     */
    private String categoryId;

    /**
     * 商家品类
     */
    private String merchantCategoryId;

    /**
     * 商家id
     */
    private String merchantId;

    /**
     * 商家角色
     */
    private String merchantRoleId;

    /**
     * 商家名称
     */
    private String merchantName;

}
