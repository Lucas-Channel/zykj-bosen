package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.product.constant.ProductApproveStatusEnum;
import com.bosen.product.domain.ProductDO;
import com.bosen.product.domain.ProductSkuDO;
import com.bosen.product.domain.ProductSkuMemberPriceDO;
import com.bosen.product.domain.ProductSkuWholesalePriceDO;
import com.bosen.product.mapper.ProductSkuMapper;
import com.bosen.product.service.IProductService;
import com.bosen.product.service.IProductSkuService;
import com.bosen.product.vo.request.ProductSkuQueryVO;
import com.bosen.product.vo.request.ProductSkuUpsertVO;
import com.bosen.product.vo.response.ProductSkuDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSkuDO> implements IProductSkuService {

    @Lazy
    @Resource
    private IProductService productService;

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> addSkuStock(List<ProductSkuUpsertVO> skuUpsertVOList) {
        List<ProductSkuDO> skuDOS = skuUpsertVOList.stream().map(i -> {
            ProductSkuDO skuDO = new ProductSkuDO();
            BeanUtils.copyProperties(i, skuDO);
            return skuDO;
        }).collect(Collectors.toList());
        return ResponseData.judge(this.saveOrUpdateBatch(skuDOS));
    }

    @Override
    public ResponseData<PageData<ProductSkuDetailVO>> pageList(ProductSkuQueryVO queryVO) {
        queryVO.setMerchantId(null).setMerchantRoleId(null);
        Page<ProductSkuDetailVO> page = new Page<>(queryVO.getCurrent(), queryVO.getSize());
        IPage<ProductSkuDetailVO> pageList = baseMapper.pageList(page, queryVO);
        return ResponseData.success(new PageData<>(pageList.getTotal(), pageList.getRecords()));
    }

    @Override
    public ResponseData<Void> setWholesalePrice(List<ProductSkuWholesalePriceDO> prices, String skuId) {
        ProductSkuDO skuDO = this.getById(skuId);
        if (Objects.isNull(skuDO)) {
            throw new BusinessException("商品sku不存在");
        }
        skuDO.setWholesalePrice(prices);
        return ResponseData.judge(this.updateById(skuDO));
    }

    @Override
    public ResponseData<Void> setMemberPrice(List<ProductSkuMemberPriceDO> prices, String skuId) {
        ProductSkuDO skuDO = this.getById(skuId);
        if (Objects.isNull(skuDO)) {
            throw new BusinessException("商品sku不存在");
        }
        skuDO.setMemberPrice(prices);
        return ResponseData.judge(this.updateById(skuDO));
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> updateStock(ProductSkuUpsertVO productSkuUpsertVO) {
        ProductSkuDO skuDO = this.getById(productSkuUpsertVO.getId());
        if (Objects.isNull(skuDO)) {
            throw new BusinessException(ResponseCode.SKU_NOT_EXIT_ERROR);
        }
        ProductDO productDO = productService.getById(productSkuUpsertVO.getProductId());
        if (Objects.isNull(productDO)) {
            throw new BusinessException(ResponseCode.PRODUCT_NOT_EXIT_ERROR);
        }
        // 如果允许上架期间更新库存，需要计算差量同步到es
        if (Objects.equals(productDO.getStatus(), ProductApproveStatusEnum.UP.getCode())) {
            throw new BusinessException(ResponseCode.SKU_HAS_RACKING_ERROR);
        }
        boolean update = this.lambdaUpdate().set(ProductSkuDO::getStockNum, productSkuUpsertVO.getStockNum())
                .eq(ProductSkuDO::getId, productSkuUpsertVO.getId())
                .eq(ProductSkuDO::getMerchantId, null)
                .eq(ProductSkuDO::getMerchantRoleId, null)
                .update();
        return ResponseData.judge(update);
    }
}
