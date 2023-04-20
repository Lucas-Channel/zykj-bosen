package com.bosen.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosen.product.domain.ProductSkuDO;
import com.bosen.product.vo.request.ProductSkuQueryVO;
import com.bosen.product.vo.response.ProductSkuDetailVO;
import org.apache.ibatis.annotations.Param;

public interface ProductSkuMapper extends BaseMapper<ProductSkuDO> {
    IPage<ProductSkuDetailVO> pageList(Page<ProductSkuDetailVO> page, @Param("queryParams") ProductSkuQueryVO queryVO);
}
