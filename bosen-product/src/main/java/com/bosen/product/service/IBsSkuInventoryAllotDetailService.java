package com.bosen.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.product.domain.BsSkuInventoryAllotDetailDO;
import com.bosen.product.vo.request.SkuInventoryAllotDetailVO;

import java.util.List;

/**
 * 仓位库存调拨明细(BsSkuInventoryAllotDetail)表服务接口
 *
 * @author Lucas
 * @since 2023-07-14 10:08:18
 */
public interface IBsSkuInventoryAllotDetailService extends IService<BsSkuInventoryAllotDetailDO> {
    /**
     * 添加调拨单明细
     * @param detail 明细
     */
    void addAllotDetail(List<SkuInventoryAllotDetailVO> detail, String allotId);
}

