package com.bosen.elasticsearch.domain;

import com.bosen.elasticsearch.es.ESConstant;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * c端商品响应类-es模型
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/21
 */
@Data
@Accessors(chain = true)
@Document(indexName = ESConstant.ES_PRODUCT_INDEX)
@Setting(settingPath = "elasticsearch/settings.json")
public class ESProductSkuModelDO implements Serializable {

    private static final long serialVersionUID = -5351013175951661017L;

    @Field(type = FieldType.Keyword)
    private String spuId;

    /**
     * 商品类型
     */
    @Field(type = FieldType.Integer)
    private Integer productType;

    @Field(type = FieldType.Keyword)
    private String categoryId;

    @Field(type = FieldType.Keyword)
    private String merchantCategoryId;

    @Field(type = FieldType.Keyword)
    private String brandId;

    @Field(type = FieldType.Keyword)
    private String storeId;

    @MultiField(mainField = @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word"),
            otherFields = @InnerField(suffix = "inner", type = FieldType.Text, analyzer = "pinyin"))
    private String storeName;

    @Field(type = FieldType.Keyword)
    private String shopId;

    @Field(type = FieldType.Keyword)
    private String shopName;

    @Field(type = FieldType.Keyword)
    private String skuId;

    @Field(type = FieldType.Keyword)
    private String skuImg;

    @MultiField(mainField = @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word"),
            otherFields = @InnerField(suffix = "inner", type = FieldType.Text, analyzer = "pinyin"))
    private String skuName;

    @Field(type = FieldType.Nested)
    private String[] album;

    /**
     * 原价
     */
    private BigDecimal originPrice;

    /**
     * 销售/零售价
     */
    private BigDecimal salesPrice;

    /**
     * vip价格
     */
    private BigDecimal vipPrice;

    /**
     * 该sku下的属性与规格
     */
    @Field(type = FieldType.Nested)
    private List<ESProductAttributeAndValueModelDO> attrs;
}
