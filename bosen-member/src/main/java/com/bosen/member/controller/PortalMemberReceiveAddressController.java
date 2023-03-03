package com.bosen.member.controller;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.member.domain.PortalMemberReceiveAddressDO;
import com.bosen.member.service.IMemberReceiveAddressService;
import com.bosen.member.vo.response.MemberReceiveAddressDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/27
 */
@RestController
@RequestMapping("/member/receive")
public class PortalMemberReceiveAddressController {

    @Resource
    private IMemberReceiveAddressService memberReceiveAddressService;

    @GetMapping("/listCurrentMemberAddress")
    public ResponseData<List<MemberReceiveAddressDetailVO>> listCurrentMemberAddress() {
        return memberReceiveAddressService.listCurrentMemberAddress();
    }

    /**
     * 新增/修改地址
     * @param memberReceiveAddressDO 参数
     * @return 结果
     */
    @PostMapping("/upsertMemberAddress")
    public ResponseData<Void> upsertMemberAddress(PortalMemberReceiveAddressDO memberReceiveAddressDO) {
        return memberReceiveAddressService.upsertMemberAddress(memberReceiveAddressDO);
    }

    /**
     * 删除地址
     * @param addressId 地址id
     * @return 结果
     */
    @PostMapping("/deleteMemberAddressById/{id}")
    public ResponseData<Void> deleteMemberAddressById(@PathVariable("id") Long addressId) {
        return memberReceiveAddressService.deleteMemberAddressById(addressId);
    }

}
