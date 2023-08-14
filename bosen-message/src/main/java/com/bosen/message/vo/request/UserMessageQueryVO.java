package com.bosen.message.vo.request;

import com.bosen.common.domain.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 分页查询
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserMessageQueryVO extends PageVO implements Serializable {

    private static final long serialVersionUID = 691120802745281318L;

    private String title;

    /**
     * 状态
     **/
    private Integer status;

    /**
     * 消息类型：1、系统消息，2、其他消息
     **/
    private Integer type;

    /**
     * 业务消息类型：1、交易消息，2、订单消息， 3、售后，4、物流，5、库存，6、会员，7、其他
     **/
    private Integer businessType;
}
