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

    private Long categoryId;

}
