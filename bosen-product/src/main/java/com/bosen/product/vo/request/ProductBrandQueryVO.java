package com.bosen.product.vo.request;

import com.bosen.common.domain.PageVO;
import lombok.Data;


@Data
public class ProductBrandQueryVO extends PageVO {
    private String brandName;
}
