package com.bosen.member.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("bs_portal_member_login_log")
public class PortalMemberLoginLogDO implements Serializable {
    private static final long serialVersionUID = -8877555605442480963L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long memberId;

    private Date createTime;

    private String ip;

    private String city;

    /**
     * 登录类型：0->PC；1->android;2->ios;3->小程序
     */
    private Integer loginType;

    private String province;

}