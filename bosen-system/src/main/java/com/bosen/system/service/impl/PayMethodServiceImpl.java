package com.bosen.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.system.domain.PayMethodDO;
import com.bosen.system.mapper.PayMethodMapper;
import com.bosen.system.service.IPayMethodService;
import com.bosen.system.vo.request.PayMethodUpsertVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/6
 */
@Service
public class PayMethodServiceImpl extends ServiceImpl<PayMethodMapper, PayMethodDO> implements IPayMethodService {
    @Override
    public ResponseData<Void> upsertPayMethod(PayMethodUpsertVO upsertVO) {
        PayMethodDO payMethodDO = new PayMethodDO();
        BeanUtils.copyProperties(upsertVO, payMethodDO);
        return ResponseData.judge(this.saveOrUpdate(payMethodDO));
    }

    @Override
    public ResponseData<Void> updateStatus(Long id, Integer status) {
        return ResponseData.judge(this.lambdaUpdate().eq(PayMethodDO::getId, id).set(PayMethodDO::getEnableFlag, status).update());
    }
}
