package com.bosen.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.system.domain.SysDictDO;
import com.bosen.system.vo.request.DictQueryVO;
import com.bosen.system.vo.request.DictUpsertVO;
import com.bosen.system.vo.response.DictDetailVO;

/**
 * 字典管理(BsSysDict)表服务接口
 *
 * @author Lucas
 * @since 2023-05-25 16:34:05
 */
public interface ISysDictService extends IService<SysDictDO> {

    ResponseData<PageData<DictDetailVO>> pageList(DictQueryVO pageVO);

    ResponseData<Void> upsert(DictUpsertVO dictUpsertVO);
}

