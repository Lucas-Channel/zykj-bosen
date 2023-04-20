package com.bosen.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosen.product.domain.ProductDO;
import com.bosen.product.vo.request.ProductQueryVO;
import com.bosen.product.vo.response.ProductDetailVO;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper extends BaseMapper<ProductDO> {
    IPage<ProductDetailVO> listPages(Page<ProductDetailVO> page, @Param("queryParams") ProductQueryVO queryVO);

    ProductDetailVO detailById(String id);
}
