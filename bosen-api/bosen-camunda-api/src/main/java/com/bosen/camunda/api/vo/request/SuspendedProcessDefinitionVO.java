package com.bosen.camunda.api.vo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 挂起或激活流程定义对象
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/28
 */
@Data
public class SuspendedProcessDefinitionVO implements Serializable {
    private static final long serialVersionUID = 4502564831351951119L;

    @NotEmpty(message = "流程定义对象id不能为空")
    private String processDefinitionId;

    /**
     * true 挂起，false 激活
     */
    @NotNull(message = "挂起/激活标志不能为空")
    private Boolean suspended;

    private Boolean includeProcessInstances = true;
}
