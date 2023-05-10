package com.bosen.product.vo.request;

import com.bosen.common.domain.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分类品牌：一个分类多个品牌
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductCategoryBrandQueryVO extends PageVO {

    private String categoryName;

    private String brandName;

}
