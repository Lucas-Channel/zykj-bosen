package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductAttributeDO;
import com.bosen.product.domain.ProductAttributeValueDO;
import com.bosen.product.mapper.ProductAttributeMapper;
import com.bosen.product.service.IProductAttributeService;
import com.bosen.product.service.IProductAttributeValueService;
import com.bosen.product.vo.request.ProductAttributeUpsertVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttributeDO> implements IProductAttributeService {

    @Resource
    private IProductAttributeValueService productAttributeValueService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseData<Void> upsertProductAttribute(List<ProductAttributeUpsertVO> attrList, List<ProductAttributeUpsertVO> specList, String productId) {
        // step2.1 删除历史属性和规格
        this.lambdaUpdate().eq(ProductAttributeDO::getProductId, productId).remove();
        productAttributeValueService.lambdaUpdate().eq(ProductAttributeValueDO::getProductId, productId).remove();
        // step2.2 保存商品属性
        List<ProductAttributeDO> productAttributeDOS = attrList.stream().map(i -> {
            ProductAttributeDO attributeDO = new ProductAttributeDO();
            BeanUtils.copyProperties(i, attributeDO);
            attributeDO.setProductId(productId);
            return attributeDO;
        }).collect(Collectors.toList());
        this.saveBatch(productAttributeDOS);
        List<ProductAttributeValueDO> valueDOList = new CopyOnWriteArrayList<>();
        productAttributeDOS.forEach(i -> {
            List<ProductAttributeValueDO> productAttributeValueDOS = i.getValue().stream().map(val -> {
                ProductAttributeValueDO productAttributeValueDO = new ProductAttributeValueDO();
                BeanUtils.copyProperties(val, productAttributeValueDO);
                productAttributeValueDO.setProductId(productId);
                productAttributeValueDO.setProductAttributeId(i.getId());
                productAttributeValueDO.setType(2);
                return productAttributeValueDO;
            }).collect(Collectors.toList());
            valueDOList.addAll(productAttributeValueDOS);
        });
        // step2.3 保存规格
        productAttributeDOS = specList.stream().map(i -> {
            ProductAttributeDO attributeDO = new ProductAttributeDO();
            BeanUtils.copyProperties(i, attributeDO);
            attributeDO.setProductId(productId);
            return attributeDO;
        }).collect(Collectors.toList());
        this.saveBatch(productAttributeDOS);
        // step2.4 保存商品属性/规格值
        productAttributeDOS.forEach(i -> {
            List<ProductAttributeValueDO> productAttributeValueDOS = i.getValue().stream().map(val -> {
                ProductAttributeValueDO productAttributeValueDO = new ProductAttributeValueDO();
                BeanUtils.copyProperties(val, productAttributeValueDO);
                productAttributeValueDO.setProductId(productId);
                productAttributeValueDO.setProductAttributeId(i.getId());
                productAttributeValueDO.setType(1);
                return productAttributeValueDO;
            }).collect(Collectors.toList());
            valueDOList.addAll(productAttributeValueDOS);
        });
        productAttributeValueService.saveBatch(valueDOList);
        return ResponseData.success();
    }
}
