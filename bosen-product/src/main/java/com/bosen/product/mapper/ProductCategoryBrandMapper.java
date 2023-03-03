package com.bosen.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosen.product.domain.ProductCategoryBrand;
import com.bosen.product.vo.request.ProductCategoryBrandQueryVO;
import com.bosen.product.vo.response.ProductCategoryBrandDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/2
 */
public interface ProductCategoryBrandMapper extends BaseMapper<ProductCategoryBrand> {

    IPage<ProductCategoryBrandDetailVO> pageList(Page<ProductCategoryBrandDetailVO> page, @Param("queryParams") ProductCategoryBrandQueryVO query);
    List<ProductCategoryBrandDetailVO> listForMobile(@Param("queryParams") ProductCategoryBrandQueryVO query);

}
