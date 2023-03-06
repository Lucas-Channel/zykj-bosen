package com.bosen.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.system.domain.PayMethodParamsDO;
import com.bosen.system.mapper.PayMethodParamsMapper;
import com.bosen.system.service.IPayMethodParamsService;
import com.bosen.system.vo.request.PayMethodParamsUpsertVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/6
 */
@Service
public class PayMethodParamsServiceImpl extends ServiceImpl<PayMethodParamsMapper, PayMethodParamsDO> implements IPayMethodParamsService {

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> upsertParams(List<PayMethodParamsUpsertVO> upsertVO) {
        List<PayMethodParamsDO> collect = upsertVO.stream().map(i -> {
            PayMethodParamsDO payMethodParamsDO = new PayMethodParamsDO();
            BeanUtils.copyProperties(i, payMethodParamsDO);
            return payMethodParamsDO;
        }).collect(Collectors.toList());
        return ResponseData.judge(this.saveOrUpdateBatch(collect));
    }
}
