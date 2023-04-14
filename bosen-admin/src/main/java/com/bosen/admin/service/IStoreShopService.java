package com.bosen.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.admin.domain.StoreShopDO;
import com.bosen.admin.vo.response.StoreShopDetailVO;
import com.bosen.admin.vo.resquest.StoreShopApplyVO;
import com.bosen.admin.vo.resquest.StoreShopQueryVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.vo.request.ApproveBatchInfoVO;
import com.bosen.common.vo.request.ApproveInfoVO;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
public interface IStoreShopService extends IService<StoreShopDO> {

    ResponseData<Void> openStoreShopApply(StoreShopApplyVO storeShopApplyVO);

    ResponseData<Void> closeStoreShopApply(StoreShopApplyVO storeShopApplyVO);

    ResponseData<List<StoreShopDetailVO>> listStoreShopForMerchant(StoreShopQueryVO queryVO);

    ResponseData<PageData<StoreShopDetailVO>> pageList(StoreShopQueryVO queryVO);

    ResponseData<Void> approveOpenStoreShopApply(ApproveInfoVO approveInfoVO);

    ResponseData<Void> approveOpenStoreShopApplyBatch(ApproveBatchInfoVO approveBatchInfoVO);

    ResponseData<Void> approveCloseStoreShopApplyBatch(ApproveBatchInfoVO approveBatchInfoVO);

    ResponseData<Void> approveCloseStoreShopApply(ApproveInfoVO approveInfoVO);
}
