package com.bosen.product.vo.request;

import lombok.Data;

@Data
public class ProductAttributeUpsertVO {

    private String id;

    private Long  attributeId;

    private String name;

    private String value;

    private String picUrl;

}
