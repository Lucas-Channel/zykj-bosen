package com.bosen.search.mapper;

import com.bosen.elasticsearch.domain.ESProductSkuModelDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/9
 */
public interface EsProductMapper extends ElasticsearchRepository<ESProductSkuModelDO, String> {
}
