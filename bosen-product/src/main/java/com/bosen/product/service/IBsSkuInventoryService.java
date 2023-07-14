package com.bosen.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.BsSkuInventoryDO;
import com.bosen.product.vo.request.SkuInventoryQueryVO;
import com.bosen.product.vo.request.SkuInventoryUpsertVO;
import com.bosen.product.vo.response.SkuInventoryDetailVO;

import java.util.List;

/**
 * 商品SKU库存(BsSkuInventory)表服务接口
 *
 * @author Lucas
 * @since 2023-07-14 10:08:18
 */
public interface IBsSkuInventoryService extends IService<BsSkuInventoryDO> {
    List<BsSkuInventoryDO> listBySkuId(List<String> skuIds);

    ResponseData<PageData<SkuInventoryDetailVO>> pageList(SkuInventoryQueryVO pageVO);

    ResponseData<Void> upsert(SkuInventoryUpsertVO bsSkuInventory);
}

