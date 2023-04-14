package com.bosen.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.admin.domain.StoreDO;
import com.bosen.admin.vo.response.StoreDetailVO;
import com.bosen.admin.vo.resquest.StoreQueryVO;
import com.bosen.admin.vo.resquest.StoreUpsertVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
public interface IStoreService extends IService<StoreDO> {
    ResponseData<PageData<StoreDetailVO>> pageList(StoreQueryVO queryVO);

    ResponseData<Void> upsertStore(StoreUpsertVO storeUpsertVO);

    ResponseData<List<StoreDetailVO>> getAllStore();
}
