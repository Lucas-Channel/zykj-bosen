package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.exception.BusinessException;
import com.bosen.product.domain.BsSkuInventoryAllotDetailDO;
import com.bosen.product.mapper.BsSkuInventoryAllotDetailMapper;
import com.bosen.product.service.IBsSkuInventoryAllotDetailService;
import com.bosen.product.vo.request.SkuInventoryAllotDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 仓位库存调拨明细(BsSkuInventoryAllotDetail)表服务实现类
 *
 * @author Lucas
 * @since 2023-07-14 10:08:18
 */
@Service
public class BsSkuInventoryAllotDetailServiceImpl extends ServiceImpl<BsSkuInventoryAllotDetailMapper, BsSkuInventoryAllotDetailDO> implements IBsSkuInventoryAllotDetailService {

    @Override
    @Transactional(rollbackFor = BusinessException.class, propagation = Propagation.REQUIRED)
    public void addAllotDetail(List<SkuInventoryAllotDetailVO> detail, String allotId) {
        this.lambdaUpdate().eq(BsSkuInventoryAllotDetailDO::getAllotId, allotId).remove();
        List<BsSkuInventoryAllotDetailDO> detailDOS = detail.stream().map(this::turnObj).collect(Collectors.toList());
        this.saveBatch(detailDOS);
    }

    private BsSkuInventoryAllotDetailDO turnObj(SkuInventoryAllotDetailVO detailVO) {
        BsSkuInventoryAllotDetailDO data = new BsSkuInventoryAllotDetailDO();
        BeanUtils.copyProperties(detailVO, data);
        return data;
    }
}

