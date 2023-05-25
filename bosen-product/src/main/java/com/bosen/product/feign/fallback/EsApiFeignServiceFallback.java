package com.bosen.product.feign.fallback;

import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.elasticsearch.domain.ESProductSkuModelDO;
import com.bosen.elasticsearch.domain.EsProductBrandDO;
import com.bosen.elasticsearch.vo.request.DownProductRequestVO;
import com.bosen.product.feign.EsApiFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/9
 */
@Slf4j
@Component
public class EsApiFeignServiceFallback implements EsApiFeignService {
    @Override
    public ResponseData<Void> rackingProduct(List<ESProductSkuModelDO> productSkuList) {
        throw new BusinessException(ResponseCode.SEARCH_SERVICE_ERROR);
    }

    @Override
    public ResponseData<Void> downProduct(DownProductRequestVO downProductRequestVO) {
        throw new BusinessException(ResponseCode.SEARCH_SERVICE_ERROR);
    }

    @Override
    public ResponseData<Void> rackingProductBrand(List<EsProductBrandDO> brandDOList) {
        throw new BusinessException(ResponseCode.SEARCH_SERVICE_ERROR);
    }

    @Override
    public ResponseData<Void> downProductBrand(List<String> ids) {
        throw new BusinessException(ResponseCode.SEARCH_SERVICE_ERROR);
    }
}
