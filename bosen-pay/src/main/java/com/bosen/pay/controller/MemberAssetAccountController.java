package com.bosen.pay.controller;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.api.MemberAssetAccountUpsertVO;
import com.bosen.pay.domain.MemberAssetAccountDO;
import com.bosen.pay.service.IMemberAssetAccountService;
import com.bosen.pay.vo.request.RechargeRequestVO;
import com.bosen.pay.vo.response.MemberAssetAccountDetail;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 会员资金账户
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/28
 */
@RestController
@RequestMapping("/member/asset/account")
public class MemberAssetAccountController {

    @Resource
    private IMemberAssetAccountService memberAssetAccountService;

    /**
     * 新增/修改资金账户
     * @return 结果
     */
    @PostMapping("/upsertAccount")
    public ResponseData<Void> upsertAccount(@RequestBody @Valid MemberAssetAccountUpsertVO memberAssetAccountUpsertVO) {
        return memberAssetAccountService.upsertAccount(memberAssetAccountUpsertVO);
    }

    /**
     * 查看资金账户信息
     * @param memberId
     * @return
     */
    @GetMapping("/detail/{memberId}")
    public ResponseData<MemberAssetAccountDetail> getAccountDetail(@PathVariable("memberId") Long memberId) {
        return memberAssetAccountService.getAccountDetail(memberId);
    }

    /**
     * 更新资金账户状态
     * @return
     */
    @PostMapping("/updateAccountStatus")
    public ResponseData<Void> updateAccountStatus(@RequestParam Long memberId, @RequestParam Integer status) {
        MemberAssetAccountDO assetAccountDO = new MemberAssetAccountDO();
        assetAccountDO.setMemberId(memberId);
        assetAccountDO.setAccountStatus(status);
        return ResponseData.judge(memberAssetAccountService.updateById(assetAccountDO));
    }

    /**
     * 账户充值
     * @return 结果
     */
    @PostMapping("/recharge")
    public ResponseData<Void> recharge(@RequestBody @Valid RechargeRequestVO rechargeRequestVO) {
        return memberAssetAccountService.recharge(rechargeRequestVO);
    }

    /**
     * 账户提现
     * @return 结果
     */
    @PostMapping("/cashOut")
    public ResponseData<Void> cashOut() {
        return memberAssetAccountService.cashOut();
    }

    /**
     * 余额支付
     * @return 结果
     */
    @PostMapping("/payBalance")
    public ResponseData<Void> payBalance() {
        return memberAssetAccountService.payBalance();
    }
}
