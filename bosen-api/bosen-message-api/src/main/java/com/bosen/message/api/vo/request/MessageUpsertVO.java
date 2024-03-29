package com.bosen.message.api.vo.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 消息(BsMessage)表实体类
 *
 * @author Lucas
 * @since 2023-08-14 15:26:30
 */
@Data
public class MessageUpsertVO implements Serializable {


    private static final long serialVersionUID = 344203514866381453L;

    private String id;

    /**
     * 消息群体类型
     */
    @NotNull(message = "消息群体类型不能为空")
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
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 内容
     **/
    @NotBlank(message = "内容不能为空")
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
     * 状态
     **/
    private Integer status;

    /**
     * 消息类型：1、系统消息，2、其他消息
     **/
    @NotNull(message = "消息类型不能为空")
    private Integer type;

    /**
     * 业务消息类型：1、交易消息，2、订单消息， 3、售后，4、物流，5、库存，6、会员，7、其他
     **/
    @NotNull(message = "业务消息类型不能为空")
    private Integer businessType;
}

