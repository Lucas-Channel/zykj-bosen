package com.bosen.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.member.domain.PortalMemberReceiveAddressDO;
import com.bosen.member.vo.response.MemberReceiveAddressDetailVO;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/27
 */
public interface IMemberReceiveAddressService extends IService<PortalMemberReceiveAddressDO> {
    /**
     * 获取当前会员的收货地址
     * @return 结果
     */
    ResponseData<List<MemberReceiveAddressDetailVO>> listCurrentMemberAddress();

    /**
     * 新增/修改地址
     * @param memberReceiveAddressDO 参数
     * @return 结果
     */
    ResponseData<Void> upsertMemberAddress(PortalMemberReceiveAddressDO memberReceiveAddressDO);

    /**
     * 删除地址
     * @param addressId 地址id
     * @return 结果
     */
    ResponseData<Void> deleteMemberAddressById(Long addressId);
}
