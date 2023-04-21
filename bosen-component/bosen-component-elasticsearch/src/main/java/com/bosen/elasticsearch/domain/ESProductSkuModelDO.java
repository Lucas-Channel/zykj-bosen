package com.bosen.elasticsearch.domain;

import com.bosen.elasticsearch.es.ESConstant;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;

/**
 * c端商品响应类
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/21
 */
@Data
@Document(indexName = ESConstant.ES_PRODUCT_INDEX)
@Setting(settingPath = "elasticsearch/settings.json")
public class ESProductSkuModelDO implements Serializable {

    private static final long serialVersionUID = -5351013175951661017L;

    private String spuId;

    private String skuId;

    private String categoryId;

    private String merchantCategoryId;
}
