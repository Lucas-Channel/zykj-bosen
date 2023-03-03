package com.bosen.product.vo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 分类品牌：一个分类多个品牌
 */
@Data
public class ProductCategoryBrandUpsertVO {
    private Long id;

    @NotNull
    private Long categoryId;

    @NotEmpty
    private List<Long> brandIds;

}
