package com.bosen.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductBrandDO;
import com.bosen.product.vo.request.BrandIdsVO;
import com.bosen.product.vo.request.ProductBrandQueryVO;
import com.bosen.product.vo.response.ProductBrandDetailVO;

public interface IProductBrandService extends IService<ProductBrandDO> {

    ResponseData<PageData<ProductBrandDetailVO>> pageList(ProductBrandQueryVO brandQueryVO);

    /**
     * 上架品牌
     * @param brandIdsVO ids
     * @return 结果
     */
    ResponseData<Void> rackingBrand(BrandIdsVO brandIdsVO);

    /**
     * 下架品牌
     * @param brandIdsVO ids
     * @return 结果
     */
    ResponseData<Void> downProductBrand(BrandIdsVO brandIdsVO);

}