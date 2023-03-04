package com.bosen.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductSkuDO;
import com.bosen.product.vo.request.ProductSkuQueryVO;
import com.bosen.product.vo.request.ProductSkuUpsertVO;
import com.bosen.product.vo.response.ProductSkuDetailVO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IProductSkuService extends IService<ProductSkuDO> {
    ResponseData<Void> addSkuStock(List<ProductSkuUpsertVO> skuUpsertVOList);

    ResponseData<PageData<ProductSkuDetailVO>> pageList(ProductSkuQueryVO queryVO);
}
