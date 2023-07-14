package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.product.domain.BsSkuInventoryDO;
import com.bosen.product.mapper.BsSkuInventoryMapper;
import com.bosen.product.service.IBsSkuInventoryService;
import com.bosen.product.vo.request.SkuInventoryQueryVO;
import com.bosen.product.vo.request.SkuInventoryUpsertVO;
import com.bosen.product.vo.response.SkuInventoryDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 商品SKU库存(BsSkuInventory)表服务实现类
 *
 * @author Lucas
 * @since 2023-07-14 10:08:18
 */
@Service
public class BsSkuInventoryServiceImpl extends ServiceImpl<BsSkuInventoryMapper, BsSkuInventoryDO> implements IBsSkuInventoryService {

    @Override
    public List<BsSkuInventoryDO> listBySkuId(List<String> skuIds) {
        return this.lambdaQuery().in(BsSkuInventoryDO::getSkuId, skuIds).eq(BsSkuInventoryDO::getDelFlag, 0).list();
    }

    @Override
    public ResponseData<PageData<SkuInventoryDetailVO>> pageList(SkuInventoryQueryVO pageVO) {
        Page<SkuInventoryDetailVO> page = new Page<>(pageVO.getCurrent(), pageVO.getSize());
        IPage<SkuInventoryDetailVO> pageList = baseMapper.pageList(page, pageVO);
        return ResponseData.success(new PageData<>(pageList.getTotal(), pageList.getRecords()));
    }

    @Override
    public ResponseData<Void> upsert(SkuInventoryUpsertVO bsSkuInventory) {
        BsSkuInventoryDO skuInventory = this.getById(bsSkuInventory.getId());
        if (Objects.isNull(skuInventory)) {
            throw new BusinessException(ResponseCode.PRODUCT_NOT_EXIT_ERROR);
        }
        BeanUtils.copyProperties(bsSkuInventory, skuInventory);
        return ResponseData.judge(this.saveOrUpdate(skuInventory));
    }
}

