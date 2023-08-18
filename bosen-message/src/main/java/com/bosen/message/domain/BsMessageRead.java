package com.bosen.message.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 消息已读列表(BsMessageRead)表实体类
 *
 * @author Lucas
 * @since 2023-08-18 11:15:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_message_read")
public class BsMessageRead extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = 6260950861946902413L;

    /**
     * 消息id
     **/
    private String messageId;

    /**
     * 已读用户id
     **/
    private String receiveUserId;

    /**
     * 已读用户角色id
     **/
    private String receiveUserRoleId;
}

