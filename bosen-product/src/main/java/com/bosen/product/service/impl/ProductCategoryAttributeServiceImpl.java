package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.product.domain.ProductCategoryAttributeDO;
import com.bosen.product.mapper.ProductCategoryAttributeMapper;
import com.bosen.product.service.IProductCategoryAttributeService;
import com.bosen.product.vo.request.ProductCategoryAttributeQueryVO;
import com.bosen.product.vo.request.ProductCategoryAttributeUpsert;
import com.bosen.product.vo.response.ProductCategoryAttributeDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductCategoryAttributeServiceImpl extends ServiceImpl<ProductCategoryAttributeMapper, ProductCategoryAttributeDO> implements IProductCategoryAttributeService {

    @Override
    public ResponseData<PageData<ProductCategoryAttributeDetailVO>> pageList(ProductCategoryAttributeQueryVO queryVO) {
        Page<ProductCategoryAttributeDO> page = this.page(new Page<>(queryVO.getCurrent(), queryVO.getSize()), new LambdaQueryWrapper<ProductCategoryAttributeDO>()
                .eq(Objects.nonNull(queryVO.getCategoryId()), ProductCategoryAttributeDO::getCategoryId, queryVO.getCategoryId())
                .like(StringUtils.hasLength(queryVO.getName()), ProductCategoryAttributeDO::getName, queryVO.getName())
                .eq(ProductCategoryAttributeDO::getType, queryVO.getType()));
        return ResponseData.success(new PageData<>(page.getTotal(), page.getRecords().stream().map(i -> {
            ProductCategoryAttributeDetailVO detailVO = new ProductCategoryAttributeDetailVO();
            BeanUtils.copyProperties(i, detailVO);
            return detailVO;
        }).collect(Collectors.toList())));
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> upsert(List<ProductCategoryAttributeUpsert> upsert) {
        List<ProductCategoryAttributeDO> attributes = upsert.stream().map(i -> {
            ProductCategoryAttributeDO productCategoryAttributeDO = new ProductCategoryAttributeDO();
            BeanUtils.copyProperties(i, productCategoryAttributeDO);
            if (Objects.nonNull(i.getCategoryId())) {
                productCategoryAttributeDO.setCreateTime(LocalDateTime.now());
            }
            return productCategoryAttributeDO;
        }).collect(Collectors.toList());
        return ResponseData.judge(this.saveOrUpdateBatch(attributes));
    }


}
