package com.bosen.elasticsearch.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * es-商品属性与规格模型
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/6
 */
@Data
@Accessors(chain = true)
public class ESProductAttributeAndValueModelDO implements Serializable {
    private static final long serialVersionUID = -5695969150921541034L;

    /**
     * 规格/属性id
     */
    private String attributeId;

    /**
     * 规格/属性名称
     */
    private String attributeName;

    /**
     * 规格/属性类型
     */
    private String attributeType;

    /**
     * 规格/属性值id
     */
    private String attributeValueId;

    /**
     * 规格/属性值val
     */
    private String attributeValue;
}
