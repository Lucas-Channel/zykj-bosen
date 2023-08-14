package com.bosen.message.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import com.bosen.message.constant.MessageTargetGroupTypeConstant;
import com.bosen.message.constant.SystemMessageStatusConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统消息(BsSystemMessage)表实体类
 *
 * @author Lucas
 * @since 2023-08-14 11:52:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_system_message")
public class BsSystemMessage extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = -5859029393747653967L;
    /**
     * 消息群体类型
     * @see MessageTargetGroupTypeConstant
     */
    private Integer messageTargetGroupType;

    /**
     * 消息群体值，逗号拼接
     */
    private String messageTargetGroupVal;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 访问地址
     */
    private String visitUrl;

    /**
     * 内容有效期
     */
    private LocalDateTime effectiveStartDate;

    /**
     * 内容有效期
     */
    private LocalDateTime effectiveEndDate;

    /**
     * 状态
     * @see SystemMessageStatusConstant
     */
    private Integer status;
}

