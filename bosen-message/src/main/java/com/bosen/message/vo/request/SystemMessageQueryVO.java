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
public class SystemMessageQueryVO extends PageVO implements Serializable {
    private static final long serialVersionUID = -6698730975235935255L;

    private String title;

    private Integer messageTargetGroupType;

    private Integer status;
}
