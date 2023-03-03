package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductBrandDO;
import com.bosen.product.mapper.ProductBrandMapper;
import com.bosen.product.service.IProductBrandService;
import com.bosen.product.vo.request.ProductBrandQueryVO;
import com.bosen.product.vo.response.ProductBrandDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

@Service
public class ProductBrandServiceImpl extends ServiceImpl<ProductBrandMapper, ProductBrandDO> implements IProductBrandService {
    @Override
    public ResponseData<PageData<ProductBrandDetailVO>> pageList(ProductBrandQueryVO brandQueryVO) {
        Page<ProductBrandDO> page = this.page(new Page<>(brandQueryVO.getCurrent(), brandQueryVO.getSize()), new LambdaQueryWrapper<ProductBrandDO>()
                .like(StringUtils.hasLength(brandQueryVO.getBrandName()), ProductBrandDO::getName, brandQueryVO.getBrandName())
                .orderByDesc(ProductBrandDO::getCreateTime));
        return ResponseData.success(new PageData<>(page.getTotal(), page.getRecords().stream().map(i -> {
            ProductBrandDetailVO detailVO = new ProductBrandDetailVO();
            BeanUtils.copyProperties(i, detailVO);
            return detailVO;
        }).collect(Collectors.toList())));
    }
}