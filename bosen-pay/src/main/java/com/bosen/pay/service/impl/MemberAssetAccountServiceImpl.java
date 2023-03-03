package com.bosen.pay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.api.MemberAssetAccountUpsertVO;
import com.bosen.pay.domain.MemberAssetAccountDO;
import com.bosen.pay.mapper.MemberAssetAccountMapper;
import com.bosen.pay.service.IAccountTradeRecordsService;
import com.bosen.pay.service.IMemberAssetAccountService;
import com.bosen.pay.vo.request.RechargeRequestVO;
import com.bosen.pay.vo.response.MemberAssetAccountDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/28
 */
@Service
public class MemberAssetAccountServiceImpl extends ServiceImpl<MemberAssetAccountMapper, MemberAssetAccountDO> implements IMemberAssetAccountService {

    @Resource
    private IAccountTradeRecordsService accountTradeRecordsService;

    @Override
    public ResponseData<Void> upsertAccount(MemberAssetAccountUpsertVO memberAssetAccountUpsertVO) {
        MemberAssetAccountDO assetAccountDO = new MemberAssetAccountDO();
        BeanUtils.copyProperties(memberAssetAccountUpsertVO, assetAccountDO);
        return ResponseData.judge(this.saveOrUpdate(assetAccountDO));
    }

    @Override
    public ResponseData<MemberAssetAccountDetail> getAccountDetail(Long memberId) {
        MemberAssetAccountDO accountDO = this.lambdaQuery().eq(MemberAssetAccountDO::getMemberId, memberId).one();
        MemberAssetAccountDetail detail = new MemberAssetAccountDetail();
        BeanUtils.copyProperties(accountDO, detail);
        return ResponseData.success(detail);
    }

    @Override
    public ResponseData<Void> recharge(RechargeRequestVO rechargeRequestVO) {
        // 获取配置
        // 依据窗口，拉起对应支付
        // 生成交易记录，交易记录状态为支付中
        // 等待回调通知，修改资金账户金额和交易记录状态
        return null;
    }

    @Override
    public ResponseData<Void> cashOut() {
        return null;
    }

    @Override
    public ResponseData<Void> payBalance() {
        return null;
    }
}
