package com.bosen.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.util.AuthUser;
import com.bosen.member.domain.PortalMemberReceiveAddressDO;
import com.bosen.member.mapper.MemberReceiveAddressServiceMapper;
import com.bosen.member.service.IMemberReceiveAddressService;
import com.bosen.member.vo.response.MemberReceiveAddressDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/27
 */
@Service
public class MemberReceiveAddressServiceImpl extends ServiceImpl<MemberReceiveAddressServiceMapper, PortalMemberReceiveAddressDO> implements IMemberReceiveAddressService {
    @Override
    public ResponseData<List<MemberReceiveAddressDetailVO>> listCurrentMemberAddress() {
        List<PortalMemberReceiveAddressDO> list = this.lambdaQuery().eq(PortalMemberReceiveAddressDO::getMemberId, AuthUser.getUserId()).list();
        if (CollectionUtils.isEmpty(list)) {
            return ResponseData.success();
        }
        List<MemberReceiveAddressDetailVO> detailVOS = list.stream().map(i -> {
            MemberReceiveAddressDetailVO detailVO = new MemberReceiveAddressDetailVO();
            BeanUtils.copyProperties(i, detailVO);
            return detailVO;
        }).collect(Collectors.toList());
        return ResponseData.success(detailVOS);
    }

    @Override
    public ResponseData<Void> upsertMemberAddress(PortalMemberReceiveAddressDO memberReceiveAddressDO) {
        return ResponseData.judge(this.saveOrUpdate(memberReceiveAddressDO));
    }

    @Override
    public ResponseData<Void> deleteMemberAddressById(Long addressId) {
        return ResponseData.judge(this.removeById(addressId));
    }


}
