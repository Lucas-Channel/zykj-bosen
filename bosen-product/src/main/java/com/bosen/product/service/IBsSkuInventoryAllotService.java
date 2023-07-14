package com.bosen.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.BsSkuInventoryAllotDO;
import com.bosen.product.vo.request.SkuInventoryAllotQueryVO;
import com.bosen.product.vo.request.SkuInventoryAllotUpsertVO;
import com.bosen.product.vo.response.SkuInventoryAllotVO;

/**
 * 仓位库存调拨(BsSkuInventoryAllot)表服务接口
 *
 * @author Lucas
 * @since 2023-07-14 10:08:18
 */
public interface IBsSkuInventoryAllotService extends IService<BsSkuInventoryAllotDO> {
    ResponseData<PageData<SkuInventoryAllotVO>> pageList(SkuInventoryAllotQueryVO queryVO);

    ResponseData<Void> upsert(SkuInventoryAllotUpsertVO skuInventoryAllotUpsertVO);
}

