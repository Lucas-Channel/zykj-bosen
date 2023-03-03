package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.product.domain.ProductAttributeDO;
import com.bosen.product.domain.ProductDO;
import com.bosen.product.domain.ProductSkuDO;
import com.bosen.product.mapper.ProductMapper;
import com.bosen.product.service.IProductAttributeService;
import com.bosen.product.service.IProductService;
import com.bosen.product.service.IProductSkuService;
import com.bosen.product.vo.request.ProductQueryVO;
import com.bosen.product.vo.request.ProductUpsertVO;
import com.bosen.product.vo.response.ProductDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductDO> implements IProductService {

    @Resource
    private IProductAttributeService productAttributeService;

    @Resource
    private IProductSkuService skuService;

    @Override
    public ResponseData<PageData<ProductDetailVO>> listPages(ProductQueryVO queryVO) {
        Page<ProductDetailVO> page = new Page<>(queryVO.getCurrent(), queryVO.getSize());
        IPage<ProductDetailVO> list = baseMapper.listPages(page, queryVO);
        return ResponseData.success(new PageData<>(list.getTotal(), list.getRecords()));
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> upsertProduct(ProductUpsertVO formData) {
        ProductDO productDO = new ProductDO();
        BeanUtils.copyProperties(formData, productDO);
        boolean saveOrUpdate = this.saveOrUpdate(productDO);
        if (saveOrUpdate) {
            // 删除历史
            productAttributeService.lambdaUpdate().eq(ProductAttributeDO::getProductId, productDO.getId()).remove();
            // 保存商品属性
            List<ProductAttributeDO> productAttributeDOS = formData.getAttrList().stream().map(i -> {
                ProductAttributeDO attributeDO = new ProductAttributeDO();
                BeanUtils.copyProperties(i, attributeDO);
                return attributeDO;
            }).collect(Collectors.toList());
            productAttributeService.saveBatch(productAttributeDOS);
            // 保存规格
            productAttributeDOS = formData.getSpecList().stream().map(i -> {
                ProductAttributeDO attributeDO = new ProductAttributeDO();
                BeanUtils.copyProperties(i, attributeDO);
                return attributeDO;
            }).collect(Collectors.toList());
            productAttributeService.saveBatch(productAttributeDOS);
            // 保存sku
            List<ProductSkuDO> skuDOS = formData.getSkuList().stream().map(i -> {
                ProductSkuDO skuDO = new ProductSkuDO();
                BeanUtils.copyProperties(i, skuDO);
                // todo 绑定商品规格，这里的第一个坑
                return skuDO;
            }).collect(Collectors.toList());
            skuService.saveBatch(skuDOS);
        } else {
            throw new BusinessException("保存商品失败");
        }
        return ResponseData.success();
    }
}
