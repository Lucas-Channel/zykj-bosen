package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.elasticsearch.domain.EsProductBrandDO;
import com.bosen.product.domain.ProductBrandDO;
import com.bosen.product.mapper.ProductBrandMapper;
import com.bosen.product.service.IProductBrandService;
import com.bosen.product.vo.request.BrandIdsVO;
import com.bosen.product.vo.request.ProductBrandQueryVO;
import com.bosen.product.vo.response.ProductBrandDetailVO;
import com.bosen.search.api.feign.EsApiFeignService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductBrandServiceImpl extends ServiceImpl<ProductBrandMapper, ProductBrandDO> implements IProductBrandService {

    @Resource
    private EsApiFeignService esApiFeignService;

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

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> rackingBrand(BrandIdsVO brandIdsVO) {
        List<ProductBrandDO> list = this.lambdaQuery().in(!brandIdsVO.getIds().isEmpty(), ProductBrandDO::getId, brandIdsVO.getIds()).list();
        List<EsProductBrandDO> doList = list.stream().map(i -> new EsProductBrandDO(i.getId(), i.getName(), i.getLogoUrl(), i.getSort())).collect(Collectors.toList());
        ResponseData<Void> data = esApiFeignService.rackingProductBrand(doList);
        if (!Objects.equals(data.getCode(), ResponseCode.SUCCESS.getCode())) {
            throw new BusinessException(ResponseCode.RACKING_PRODUCT_BRAND_ERROR);
        }
        return ResponseData.success();
    }

    @Override
    public ResponseData<Void> downProductBrand(BrandIdsVO brandIdsVO) {
        ResponseData<Void> data = esApiFeignService.downProductBrand(brandIdsVO.getIds());
        if (!Objects.equals(data.getCode(), ResponseCode.SUCCESS.getCode())) {
            throw new BusinessException(ResponseCode.DOWN_PRODUCT_BRAND_ERROR);
        }
        return ResponseData.success();
    }
}