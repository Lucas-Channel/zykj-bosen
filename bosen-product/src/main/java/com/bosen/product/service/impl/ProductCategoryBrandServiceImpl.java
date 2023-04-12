package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.product.domain.ProductCategoryBrandDO;
import com.bosen.product.mapper.ProductCategoryBrandMapper;
import com.bosen.product.service.IProductCategoryBrandService;
import com.bosen.product.vo.request.ProductCategoryBrandQueryVO;
import com.bosen.product.vo.request.ProductCategoryBrandUpsertVO;
import com.bosen.product.vo.response.ProductCategoryBrandDetailVO;
import com.bosen.product.vo.response.ProductCategoryWithBrandVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/2
 */
@Service
public class ProductCategoryBrandServiceImpl extends ServiceImpl<ProductCategoryBrandMapper, ProductCategoryBrandDO> implements IProductCategoryBrandService {
    @Override
    public ResponseData<PageData<ProductCategoryBrandDetailVO>> pageList(ProductCategoryBrandQueryVO queryVO) {
        Page<ProductCategoryBrandDetailVO> page = new Page<>(queryVO.getCurrent(), queryVO.getSize());
        IPage<ProductCategoryBrandDetailVO> pageList = baseMapper.pageList(page, queryVO);
        return ResponseData.success(new PageData<>(pageList.getTotal(), pageList.getRecords()));
    }

    @Override
    public ResponseData<List<ProductCategoryWithBrandVO>> listForMobile(ProductCategoryBrandQueryVO queryVO) {
        List<ProductCategoryBrandDetailVO> detailVOS = baseMapper.listForMobile(queryVO);
        Map<Long, List<ProductCategoryBrandDetailVO>> collect = detailVOS.stream().collect(Collectors.groupingBy(ProductCategoryBrandDetailVO::getCategoryId));
        List<ProductCategoryWithBrandVO> list = new ArrayList<>();
        collect.forEach((k, v) -> {
            ProductCategoryWithBrandVO de = new ProductCategoryWithBrandVO();
            de.setCategoryId(k);
            de.setCategoryName(v.get(0).getCategoryName());
            de.setBrandList(v);
            list.add(de);
        });
        return ResponseData.success(list);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> upsertCategoryBrand(ProductCategoryBrandUpsertVO upsertVO) {
        List<ProductCategoryBrandDO> collect = upsertVO.getBrandIds().stream().map(i -> {
            ProductCategoryBrandDO brand = new ProductCategoryBrandDO();
            brand.setBrandId(i);
            brand.setCategoryId(upsertVO.getCategoryId());
            if (Objects.isNull(upsertVO.getId())) {
                brand.setCreateTime(LocalDateTime.now());
            } else {
                // 修改只能单条修改，新增可以批量
                //brand.setId(upsertVO.getId());
            }
            return brand;
        }).collect(Collectors.toList());
        return ResponseData.judge(this.saveOrUpdateBatch(collect));
    }
}
