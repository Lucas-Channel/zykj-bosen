package com.bosen.member.vo.request;

import com.bosen.common.constant.common.SexConstant;
import com.bosen.common.constant.common.UserStatusConstant;
import com.bosen.common.domain.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class MemberQueryVO extends PageVO implements Serializable {

    private static final long serialVersionUID = 3252503498426666371L;
    /**
     * 会员等级id
     */
    private Long memberLevelId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 状态
     * @see UserStatusConstant
     */
    private Integer status;

    /**
     * 性别
     * @see SexConstant
     */
    private Integer gender;


}