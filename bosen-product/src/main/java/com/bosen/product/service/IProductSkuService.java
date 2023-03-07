package com.bosen.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductSkuDO;
import com.bosen.product.domain.ProductSkuMemberPriceDO;
import com.bosen.product.domain.ProductSkuWholesalePriceDO;
import com.bosen.product.vo.request.ProductSkuQueryVO;
import com.bosen.product.vo.request.ProductSkuUpsertVO;
import com.bosen.product.vo.response.ProductSkuDetailVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IProductSkuService extends IService<ProductSkuDO> {
    ResponseData<Void> addSkuStock(List<ProductSkuUpsertVO> skuUpsertVOList);

    ResponseData<PageData<ProductSkuDetailVO>> pageList(ProductSkuQueryVO queryVO);

    ResponseData<Void> setWholesalePrice(List<ProductSkuWholesalePriceDO> prices, Long skuId);

    ResponseData<Void> setMemberPrice(List<ProductSkuMemberPriceDO> prices, Long skuId);
}
