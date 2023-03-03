package com.bosen.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.member.domain.PortalMemberLevelDO;
import com.bosen.member.vo.request.MemberLevelQueryVO;
import com.bosen.member.vo.request.MemberLevelUpsertVO;
import com.bosen.member.vo.response.MemberLevelDetailVO;

import java.util.List;

/**
 * 会员等级业务层
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/27
 */
public interface IMemberLevelService extends IService<PortalMemberLevelDO> {
    /**
     * 获取会员默认等级
     * @return 结果
     */
    ResponseData<PortalMemberLevelDO> getDefaultMemberLevel();

    /**
     * 是否存在默认会员等级
     * @return
     */
    ResponseData<Boolean> checkExitDefaultMemberLevel();

    /**
     * 新增/修改会员等级
     * @param memberLevelUpsertVO 参数
     * @return 结果
     */
    ResponseData<Void> upsertMemberLevel(MemberLevelUpsertVO memberLevelUpsertVO);

    /**
     * 列表查询会员等级
     * @return 结果
     */
    ResponseData<List<MemberLevelDetailVO>> listMemberLevel(MemberLevelQueryVO queryVO);

    /**
     * 批量删除会员等级
     * @param ids ids
     * @return 结果
     */
    ResponseData<Void> deleteBatch(List<Long> ids);
}
