package com.bosen.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.system.domain.PayMethodParamsDO;
import com.bosen.system.vo.request.PayMethodParamsUpsertVO;
import com.bosen.system.vo.response.PayMethodParamsDetailVO;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/6
 */
public interface IPayMethodParamsService extends IService<PayMethodParamsDO> {
    ResponseData<Void> upsertParams(List<PayMethodParamsUpsertVO> upsertVO);

    ResponseData<List<PayMethodParamsDetailVO>> getParamsByPayMethId(String payMethodCode);
}
