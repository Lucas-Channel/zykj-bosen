package com.bosen.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosen.product.domain.BsSkuInventoryDO;
import com.bosen.product.vo.request.SkuInventoryQueryVO;
import com.bosen.product.vo.response.SkuInventoryDetailVO;
import org.apache.ibatis.annotations.Param;

/**
 * 商品SKU库存(BsSkuInventory)表数据库访问层
 *
 * @author Lucas
 * @since 2023-07-14 10:08:18
 */
public interface BsSkuInventoryMapper extends BaseMapper<BsSkuInventoryDO> {
    IPage<SkuInventoryDetailVO> pageList(Page<SkuInventoryDetailVO> page, @Param("queryParams") SkuInventoryQueryVO pageVO);
}

