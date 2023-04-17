package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductCategoryDO;
import com.bosen.product.mapper.ProductCategoryMapper;
import com.bosen.product.service.IProductCategoryService;
import com.bosen.product.vo.response.ProductCategoryDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategoryDO> implements IProductCategoryService {

    @Override
    public ResponseData<List<ProductCategoryDetailVO>> listCategoryTree() {
        List<ProductCategoryDO> list = this.list();
        List<ProductCategoryDetailVO> detailVOS = list.stream().filter(i -> Objects.equals(i.getParentId(), "0"))
                .map(i -> covertTreeNode(i, list)).collect(Collectors.toList());
        return ResponseData.success(detailVOS);
    }

    /**
     * 树型数据转化
     */
    private ProductCategoryDetailVO covertTreeNode(ProductCategoryDO category, List<ProductCategoryDO> categoryList) {
        ProductCategoryDetailVO node = new ProductCategoryDetailVO();
        BeanUtils.copyProperties(category, node);
        List<ProductCategoryDetailVO> children = categoryList.stream()
                .filter(i -> i.getParentId().equals(category.getId()))
                .map(i -> covertTreeNode(i, categoryList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
