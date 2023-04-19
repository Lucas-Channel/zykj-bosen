package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.constant.AttributeTypeEnum;
import com.bosen.product.domain.ProductAttributeDO;
import com.bosen.product.domain.ProductAttributeValueDO;
import com.bosen.product.mapper.ProductAttributeMapper;
import com.bosen.product.service.IProductAttributeService;
import com.bosen.product.service.IProductAttributeValueService;
import com.bosen.product.vo.request.ProductAttributeUpsertVO;
import com.bosen.product.vo.response.ProductAttributeDetailVO;
import com.bosen.product.vo.response.ProductAttributeValueDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
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
                productAttributeValueDO.setType(AttributeTypeEnum.ATTR.getCode());
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
                productAttributeValueDO.setType(AttributeTypeEnum.SPEC.getCode());
                return productAttributeValueDO;
            }).collect(Collectors.toList());
            valueDOList.addAll(productAttributeValueDOS);
        });
        productAttributeValueService.saveBatch(valueDOList);
        return ResponseData.success();
    }

    @Override
    public List<ProductAttributeDetailVO> listProductAttributeDetailByProductId(String productId) {
        List<ProductAttributeDO> attributeDOS = this.lambdaQuery().eq(ProductAttributeDO::getProductId, productId).list();
        List<ProductAttributeValueDetailVO> attributeValueDOS = productAttributeValueService.lambdaQuery().eq(ProductAttributeValueDO::getProductId, productId).list()
                .stream().map(i -> {
                    ProductAttributeValueDetailVO detailVO = new ProductAttributeValueDetailVO();
                    BeanUtils.copyProperties(i, detailVO);
                    return detailVO;
                }).collect(Collectors.toList());
        return attributeDOS.stream().map(i -> {
            ProductAttributeDetailVO detailVO = new ProductAttributeDetailVO();
            BeanUtils.copyProperties(i, detailVO);
            detailVO.setValue(attributeValueDOS.stream().filter(dt -> Objects.equals(dt.getProductAttributeId(), i.getId())).collect(Collectors.toList()));
            return detailVO;
        }).collect(Collectors.toList());
    }
}
