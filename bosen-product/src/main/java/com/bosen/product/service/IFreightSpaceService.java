package com.bosen.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.FreightSpaceDO;
import com.bosen.product.vo.request.FreightSpaceQueryVO;
import com.bosen.product.vo.request.FreightSpaceUpdateStatusVO;
import com.bosen.product.vo.request.FreightSpaceUpsertVO;
import com.bosen.product.vo.response.FreightSpaceDetailVO;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/19
 */
public interface IFreightSpaceService extends IService<FreightSpaceDO> {
    ResponseData<PageData<FreightSpaceDetailVO>> pageList(FreightSpaceQueryVO queryVO);

    ResponseData<List<FreightSpaceDetailVO>> listAll();

    ResponseData<Void> upsertFreightSpace(FreightSpaceUpsertVO upsertVO);

    ResponseData<Void> updateStatus(FreightSpaceUpdateStatusVO updateStatusVO);
}
