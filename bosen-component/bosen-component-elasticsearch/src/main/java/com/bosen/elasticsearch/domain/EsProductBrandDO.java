package com.bosen.elasticsearch.domain;

import com.bosen.elasticsearch.es.ESConstant;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/25
 */
@Data
@Accessors(chain = true)
@Document(indexName = ESConstant.ES_BRAND_INDEX)
@Setting(settingPath = "elasticsearch/settings.json")
public class EsProductBrandDO implements Serializable {
    private static final long serialVersionUID = -1558687929855171938L;
    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String brandId;

    @MultiField(mainField = @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word"),
            otherFields = @InnerField(suffix = "inner", type = FieldType.Text, analyzer = "pinyin"))
    private String brandName;

    private String logoUrl;

    private Integer sort;

    public EsProductBrandDO() {
    }

    public EsProductBrandDO(String brandId, String brandName, String logoUrl, Integer sort) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.logoUrl = logoUrl;
        this.sort = sort;
    }
}

