package com.bosen.search.service.impl;

import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.elasticsearch.domain.EsProductBrandDO;
import com.bosen.search.mapper.EsProductBrandMapper;
import com.bosen.search.service.IEsProductBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * es 品牌接口
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/25
 */
@Slf4j
@Service
public class EsProductBrandServiceImpl implements IEsProductBrandService {
    @Resource
    private EsProductBrandMapper esProductBrandMapper;

    @Override
    public ResponseData<Void> rackingProductBrand(List<EsProductBrandDO> brandDOList) {
        try {
            esProductBrandMapper.saveAll(brandDOList);
        } catch (Exception e) {
            // 可能是spring boot 和 es 的版本问题没法解析结果
            if (!e.getMessage().contains("200 OK")) {
                log.error("上架失败：》》》》》》{}", e.getMessage());
                throw new BusinessException(ResponseCode.RACKING_PRODUCT_BRAND_ERROR);
            }
        }
        return ResponseData.success();
    }

    @Override
    @Transactional
    public ResponseData<Void> downProductBrand(List<String> ids) {
        try {
            esProductBrandMapper.deleteByBrandIdIn(ids);
        } catch (Exception e) {
            if (!e.getMessage().contains("200 OK")) {
                log.error("下架商品失败：》》》》》》{}", e.getMessage());
                throw new BusinessException(ResponseCode.DOWN_PRODUCT_BRAND_ERROR);
            }
        }
        return ResponseData.success();
    }
}
