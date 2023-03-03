package com.bosen.product.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品表单对象
 */
@Data
public class ProductUpsertVO {

    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Long categoryId;
    private Long brandId;
    private BigDecimal originPrice;
    private BigDecimal salesPrice;
    private String picUrl;
    private String[] album;
    private String description;
    private String detail;
    @NotEmpty
    private List<ProductAttributeUpsertVO> attrList;
    @NotEmpty
    private List<ProductAttributeUpsertVO> specList;
    @NotEmpty
    private List<ProductSkuUpsertVO> skuList;
}
