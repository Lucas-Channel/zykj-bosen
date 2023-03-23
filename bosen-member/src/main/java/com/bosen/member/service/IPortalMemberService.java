package com.bosen.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.MemberCacheVO;
import com.bosen.common.domain.UserDto;
import com.bosen.member.domain.PortalMemberDO;
import com.bosen.member.vo.request.MemberQueryVO;
import com.bosen.member.vo.request.MemberRegisterVO;
import com.bosen.member.vo.request.MemberUpdateVO;
import com.bosen.member.vo.response.MemberDetailVO;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
public interface IPortalMemberService extends IService<PortalMemberDO> {
    /**
     * 获取用户信息，可以是手机号或者是用户名
     */
    UserDto loadByUsername(String username);

    /**
     * 注册会员
     * @param memberRegisterVO 资料
     * @return 结果
     */
    ResponseData<Void> register(MemberRegisterVO memberRegisterVO);

    /**
     * 分页查询会员列表数据
     * @param queryVO 查询参数
     * @return 结果
     */
    ResponseData<PageData<MemberDetailVO>> pageMemberList(MemberQueryVO queryVO);

    /**
     * 查询会员详情
     * @param id 会员id
     * @return 结果
     */
    ResponseData<MemberDetailVO> getMemberInfo(Long id);

    /**
     * 更新会员资料
     * @param updateVO 参数
     * @return 结果
     */
    ResponseData<Void> updateMemberInfo(MemberUpdateVO updateVO);

    /**
     * 修改会员状态
     * @param id id
     * @param status 状态
     * @return 结果
     */
    ResponseData<Void> updateStatus(Long id, Integer status);

    MemberCacheVO getCurrentMember(Long memberId);
}
