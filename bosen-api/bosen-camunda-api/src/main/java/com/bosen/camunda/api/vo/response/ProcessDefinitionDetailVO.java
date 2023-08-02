package com.bosen.camunda.api.vo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 流程定义对象
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/20
 */
@Data
public class ProcessDefinitionDetailVO implements Serializable {
    private static final long serialVersionUID = -4188544620422630262L;

    private String id;

    private String processDefinitionKey;

    private String resourceName;

    private String processDefinitionName;
    /**
     * 此进程定义是否具有开始表单键。
     */
    private Boolean hasStartFormKey;

    private Boolean startTableInTaskList;
    /**
     * 流程实例是否停止,如果进程定义处于挂起状态，则返回true。
     */
    private Boolean suspended;

}
