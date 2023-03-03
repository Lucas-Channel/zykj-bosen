package com.bosen.product.vo.response;

import com.bosen.common.domain.PageVO;
import lombok.Data;

import java.io.Serializable;


@Data
public class ProductBrandDetailVO extends PageVO implements Serializable {
    private static final long serialVersionUID = 2535582284868326779L;
    private Long id;

    private String name;

    private String logoUrl;

    private Integer sort;
}
