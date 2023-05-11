package com.bosen.search.mapper;

import com.bosen.elasticsearch.domain.ESProductSkuModelDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * es 仓库 直接使用jpa写法
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/9
 */
@Repository
public interface EsProductMapper extends ElasticsearchRepository<ESProductSkuModelDO, String> {

    List<ESProductSkuModelDO> findBySkuIdIn(List<String> skuIds);

    List<ESProductSkuModelDO> findBySpuId(String spuId);
}
