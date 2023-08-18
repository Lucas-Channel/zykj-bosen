package com.bosen.message.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 消息(BsMessage)表实体类
 *
 * @author Lucas
 * @since 2023-08-18 11:14:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_message")
public class BsMessage extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = 7225337345620019509L;
    /**
     * 消息群体类型
     **/
    private Integer messageTargetGroupType;

    /**
     * 接收人id
     **/
    private String receiveUserId;

    /**
     * 接收人角色id
     **/
    private String receiveUserRoleId;

    /**
     * 发送人id
     **/
    private String sendUserId;

    /**
     * 发送人角色id
     **/
    private String sendUserRoleId;

    /**
     * 标题
     **/
    private String title;

    /**
     * 内容
     **/
    private String content;

    /**
     * 访问地址
     **/
    private String visitUrl;

    /**
     * 内容有效期
     **/
    private LocalDateTime effectiveStartDate;

    /**
     * 内容有效期
     **/
    private LocalDateTime effectiveEndDate;

    /**
     * 消息类型：1、系统消息，2、其他消息
     **/
    private Integer type;

    /**
     * 业务消息类型：1、交易消息，2、订单消息， 3、售后，4、物流，5、库存，6、会员，7、其他， 8、系统
     **/
    private Integer businessType;

}

