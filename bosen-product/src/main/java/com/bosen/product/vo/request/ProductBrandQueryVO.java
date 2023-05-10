package com.bosen.product.vo.request;

import com.bosen.common.domain.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class ProductBrandQueryVO extends PageVO {
    private String brandName;
}
