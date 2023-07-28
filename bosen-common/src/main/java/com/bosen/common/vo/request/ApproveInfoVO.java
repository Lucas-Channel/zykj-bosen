package com.bosen.common.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 通用审批
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/10
 */
@Data
public class ApproveInfoVO implements Serializable {
    private static final long serialVersionUID = 3226537671033552374L;

    /**
     * 是否同意
     */
    @NotNull(message = "审批状态不能为空")
    private Integer agree;

    /**
     * 原因
     */
    @NotBlank(message = "审核原因不能为空")
    private String reason;

    /**
     * 原单id
     */
    @NotBlank(message = "原单id不能为空")
    private String originalId;

    /**
     * 流程实例id
     */
    private String processInstanceId;
}
