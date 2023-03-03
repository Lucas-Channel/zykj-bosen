package com.bosen.product.vo.response;

import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryDetailVO extends BaseEntityData {

    private String name;

    private Long parentId;

    private String iconUrl;

    private Integer level;

    private Integer sort;

    private List<ProductCategoryDetailVO> children;
}
