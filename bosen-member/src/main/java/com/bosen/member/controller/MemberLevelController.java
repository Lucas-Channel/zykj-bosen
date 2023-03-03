package com.bosen.member.controller;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.member.domain.PortalMemberLevelDO;
import com.bosen.member.service.IMemberLevelService;
import com.bosen.member.vo.request.MemberLevelQueryVO;
import com.bosen.member.vo.request.MemberLevelUpsertVO;
import com.bosen.member.vo.response.MemberLevelDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 会员等级管理
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/27
 */
@RestController
@RequestMapping("/member/level")
public class MemberLevelController {
    @Resource
    private IMemberLevelService memberLevelService;

    /**
     * 查询会员等级列表数据
     * @param queryVO 查询参数
     * @return 结果
     */
    @GetMapping("/list")
    public ResponseData<List<MemberLevelDetailVO>> list(MemberLevelQueryVO queryVO) {
        return memberLevelService.listMemberLevel(queryVO);
    }

    /**
     * 获取默认会员等级
     * @return
     */
    @GetMapping("/getDefaultMemberLevel")
    public ResponseData<PortalMemberLevelDO> getDefaultMemberLevel() {
        return memberLevelService.getDefaultMemberLevel();
    }

    /**
     * 新增/修改会员等级
     * @param memberLevelUpsertVO 参数
     * @return 结果
     */
    @PostMapping("/upsertMemberLevel")
    public ResponseData<Void> upsertMemberLevel(@RequestBody @Valid MemberLevelUpsertVO memberLevelUpsertVO) {
        return memberLevelService.upsertMemberLevel(memberLevelUpsertVO);
    }
    /**
     * 批量删除会员等级
     * @param ids ids
     * @return 结果
     */
    @PostMapping("/deleteBatch")
    public ResponseData<Void> deleteBatch(@RequestBody @Valid @NotEmpty List<Long> ids) {
        return memberLevelService.deleteBatch(ids);
    }
}
