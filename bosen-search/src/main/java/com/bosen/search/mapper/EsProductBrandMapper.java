package com.bosen.search.mapper;

import com.bosen.elasticsearch.domain.EsProductBrandDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * es 品牌 仓库 直接使用jpa写法
 * @author Lucas
 * @version 2.0.0
 */
@Repository
public interface EsProductBrandMapper extends ElasticsearchRepository<EsProductBrandDO, String> {
    void deleteByBrandIdIn(List<String> ids);
}
