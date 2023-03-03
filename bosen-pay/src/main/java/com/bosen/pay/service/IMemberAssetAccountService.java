package com.bosen.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.api.MemberAssetAccountUpsertVO;
import com.bosen.pay.domain.MemberAssetAccountDO;
import com.bosen.pay.vo.request.RechargeRequestVO;
import com.bosen.pay.vo.response.MemberAssetAccountDetail;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/28
 */
public interface IMemberAssetAccountService extends IService<MemberAssetAccountDO> {
    ResponseData<Void> upsertAccount(MemberAssetAccountUpsertVO memberAssetAccountUpsertVO);

    ResponseData<MemberAssetAccountDetail> getAccountDetail(Long memberId);

    ResponseData<Void> recharge(RechargeRequestVO rechargeRequestVO);

    ResponseData<Void> cashOut();

    ResponseData<Void> payBalance();
}
