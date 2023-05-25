package com.bosen.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.system.domain.SysDictItemDO;
import com.bosen.system.vo.request.DictItemQueryVO;
import com.bosen.system.vo.request.DictItemUpsertVO;
import com.bosen.system.vo.response.DictItemDetailVO;

/**
 * 字典管理明细(BsSysDictItem)表服务接口
 *
 * @author Lucas
 * @since 2023-05-25 16:34:05
 */
public interface ISysDictItemService extends IService<SysDictItemDO> {
    ResponseData<PageData<DictItemDetailVO>> pageList(DictItemQueryVO pageVO);

    ResponseData<Void> upsert(DictItemUpsertVO dictItemUpsertVO);
}

