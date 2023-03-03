package com.bosen.member.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.constant.common.SexConstant;
import com.bosen.common.constant.common.UserStatusConstant;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@TableName(value = "bs_portal_member_b")
public class PortalMemberDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = 7922701013677458397L;

    /**
     * 会员等级id
     */
    private Long memberLevelId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

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
     * 头像
     */
    private String icon;

    /**
     * 性别
     * @see SexConstant
     */
    private Integer gender;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 职业
     */
    private String job;

    /**
     * 个性签名
     */
    private String personalizedSignature;

    /**
     * 用户来源
     */
    private Integer sourceType;

    /**
     * 积分
     */
    private BigDecimal integration;

    /**
     * 成长值
     */
    private BigDecimal growth;

    /**
     * 剩余抽奖次数
     */
    private Integer luckyCount;

    /**
     * 历史积分数量
     */
    private BigDecimal historyIntegration;

    /**
     * 身份证
     */
    private String idCard;

    private String openId;
}