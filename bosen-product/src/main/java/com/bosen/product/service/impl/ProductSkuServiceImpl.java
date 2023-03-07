package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.product.domain.ProductSkuDO;
import com.bosen.product.domain.ProductSkuMemberPriceDO;
import com.bosen.product.domain.ProductSkuWholesalePriceDO;
import com.bosen.product.mapper.ProductSkuMapper;
import com.bosen.product.service.IProductSkuService;
import com.bosen.product.vo.request.ProductSkuQueryVO;
import com.bosen.product.vo.request.ProductSkuUpsertVO;
import com.bosen.product.vo.response.ProductSkuDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSkuDO> implements IProductSkuService {

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> addSkuStock(List<ProductSkuUpsertVO> skuUpsertVOList) {
        List<ProductSkuDO> skuDOS = skuUpsertVOList.stream().map(i -> {
            ProductSkuDO skuDO = new ProductSkuDO();
            BeanUtils.copyProperties(i, skuDO);
            return skuDO;
        }).collect(Collectors.toList());
        return ResponseData.judge(this.saveBatch(skuDOS));
    }

    @Override
    public ResponseData<PageData<ProductSkuDetailVO>> pageList(ProductSkuQueryVO queryVO) {
        Page<ProductSkuDO> page = this.page(new Page<>(queryVO.getCurrent(), queryVO.getSize()), new LambdaQueryWrapper<ProductSkuDO>()
                .like(StringUtils.hasLength(queryVO.getSkuName()), ProductSkuDO::getName, queryVO.getSkuName())
                .like(StringUtils.hasLength(queryVO.getSkuCode()), ProductSkuDO::getSkuCode, queryVO.getSkuCode())
                .orderByDesc(ProductSkuDO::getCreateTime));
        return ResponseData.success(new PageData<>(page.getTotal(), page.getRecords().stream().map(i -> {
            ProductSkuDetailVO detailVO = new ProductSkuDetailVO();
            BeanUtils.copyProperties(i, detailVO);
            return detailVO;
        }).collect(Collectors.toList())));
    }

    @Override
    public ResponseData<Void> setWholesalePrice(List<ProductSkuWholesalePriceDO> prices, Long skuId) {
        ProductSkuDO skuDO = this.getById(skuId);
        if (Objects.isNull(skuDO)) {
            throw new BusinessException("商品sku不存在");
        }
        skuDO.setWholesalePrice(prices);
        return ResponseData.judge(this.updateById(skuDO));
    }

    @Override
    public ResponseData<Void> setMemberPrice(List<ProductSkuMemberPriceDO> prices, Long skuId) {
        ProductSkuDO skuDO = this.getById(skuId);
        if (Objects.isNull(skuDO)) {
            throw new BusinessException("商品sku不存在");
        }
        skuDO.setMemberPrice(prices);
        return ResponseData.judge(this.updateById(skuDO));
    }
}
