package com.bosen.product.vo.response;

import lombok.Data;

import java.io.Serializable;


@Data
public class ProductBrandDetailVO implements Serializable {
    private static final long serialVersionUID = 2535582284868326779L;
    private String id;

    private String name;

    private String logoUrl;

    private Integer sort;
}
