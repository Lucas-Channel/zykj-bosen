package com.bosen.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosen.admin.domain.StoreShopDO;
import com.bosen.admin.vo.response.StoreShopDetailVO;
import com.bosen.admin.vo.resquest.StoreShopQueryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
public interface StoreShopMapper extends BaseMapper<StoreShopDO> {

    List<StoreShopDetailVO> listStoreShopForMerchant(@Param("queryVO") StoreShopQueryVO queryVO, @Param("merchantId") String merchantId, @Param("merchantRoleId") String merchantRoleId);

    IPage<StoreShopDetailVO> pageList(Page<StoreShopDetailVO> page, @Param("queryVO") StoreShopQueryVO queryVO);

}
