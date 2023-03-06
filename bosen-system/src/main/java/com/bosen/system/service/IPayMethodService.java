package com.bosen.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.system.domain.PayMethodDO;
import com.bosen.system.vo.request.PayMethodUpsertVO;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/6
 */
public interface IPayMethodService extends IService<PayMethodDO> {
    ResponseData<Void> upsertPayMethod(PayMethodUpsertVO upsertVO);

    ResponseData<Void> updateStatus(Long id, Integer status);
}
