package com.bosen.message.vo.response;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 明细
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/14
 */
@Data
public class BsSystemMessageDetailVO implements Serializable {
    private static final long serialVersionUID = -8523163079995131347L;

    private String id;

    /**
     * 消息群体类型
     */
    private Integer messageTargetGroupType;

    private String messageTargetGroupTypeName;

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
     * 状态：1、草稿，2、已发布，3、作废，4、已过期
     */
    private Integer status;

    private String statusName;
}
