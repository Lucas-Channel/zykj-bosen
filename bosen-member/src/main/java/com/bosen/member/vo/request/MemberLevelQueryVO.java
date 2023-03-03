package com.bosen.member.vo.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 消费会员等级-查询
 */
@Data
public class MemberLevelQueryVO implements Serializable {

    private static final long serialVersionUID = -6227726301858383864L;

    /**
     * 等级名称
     */
    private String name;

    /**
     * 是否为默认等级：0->不是；1->是
     */
    private Integer defaultStatus;

}