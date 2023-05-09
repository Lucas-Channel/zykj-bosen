package com.bosen.search.service.impl;

import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.elasticsearch.domain.ESProductSkuModelDO;
import com.bosen.search.mapper.EsProductMapper;
import com.bosen.search.service.IEsProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * es 商品搜索接口
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/9
 */
@Slf4j
@Service
public class EsProductServiceImpl implements IEsProductService {
    @Resource
    private EsProductMapper esProductMapper;

    @Override
    @Transactional
    public ResponseData<Void> rackingProduct(List<ESProductSkuModelDO> productSkuList) {
        try {
            esProductMapper.saveAll(productSkuList);
        } catch (Exception e) {
            if (!e.getMessage().contains("200 OK")) {
                log.error("上架失败：》》》》》》{}", e.getMessage());
                throw new BusinessException(ResponseCode.RACKING_PRODUCT_ERROR);
            }
        }
        return ResponseData.success();
    }
}
