package com.bosen.member.controller;

import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.UserDto;
import com.bosen.member.service.IPortalMemberService;
import com.bosen.member.vo.request.MemberQueryVO;
import com.bosen.member.vo.request.MemberRegisterVO;
import com.bosen.member.vo.request.MemberUpdateVO;
import com.bosen.member.vo.response.MemberDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
@RestController
@RequestMapping("/member")
public class PortalMemberController {
    @Resource
    private IPortalMemberService portalMemberService;

    /**
     * 用于登录接口
     * @param username 手机号或者是用户名称
     * @return 结果
     */
    @GetMapping("/loadByUsername")
    public UserDto loadByUsername(@RequestParam String username) {
        return portalMemberService.loadByUsername(username);
    }

    /**
     * 会员注册
     * @param memberRegisterVO 会员注册参数
     * @return 结果
     */
    @PostMapping("/register")
    public ResponseData<Void> register(@RequestBody @Valid MemberRegisterVO memberRegisterVO) {
        return portalMemberService.register(memberRegisterVO);
    }

    /**
     * 分页获取会员列表
     * @param queryVO 查询参数
     * @return 结果
     */
    @GetMapping("/pageMemberList")
    public ResponseData<PageData<MemberDetailVO>> pageMemberList(MemberQueryVO queryVO) {
        return portalMemberService.pageMemberList(queryVO);
    }

    /**
     * 获取会员详情信息
     * @return 结果
     */
    @GetMapping("/getMemberInfo/{id}")
    public ResponseData<MemberDetailVO> getMemberInfo(@PathVariable("id") Long id) {
        return portalMemberService.getMemberInfo(id);
    }

    /**
     * 更新会员信息
     * @return 结果
     */
    @PostMapping("/updateMemberInfo")
    public ResponseData<Void> updateMemberInfo(@RequestBody MemberUpdateVO updateVO) {
        return portalMemberService.updateMemberInfo(updateVO);
    }

    /**
     * 修改会员状态
     * @param id id
     * @param status 状态
     * @return 结果
     */
    @PostMapping(value = "/updateStatus/{id}")
    public ResponseData<Void> updateStatus(@PathVariable Long id,@RequestParam(value = "status") Integer status) {
        return portalMemberService.updateStatus(id, status);
    }

    /**
     * 缓存会员信息
     * @param memberId c端用户id
     */
    @PostMapping("/cacheMemberInfo")
    public void cacheMemberInfo(@RequestBody Long memberId) {
        portalMemberService.getCurrentMember(memberId);
    }
}
