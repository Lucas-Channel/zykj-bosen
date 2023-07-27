package com.bosen.camunda.api.vo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/25
 */
@Data
public class ProcessStartTaskVO implements Serializable {
    private static final long serialVersionUID = 3128938304300037845L;


    /**
     * 流程的Key
     */
    @NotEmpty(message = "流程的Key不能为空")
    private String processKey;

    /**
     * 业务主键ID
     */
    @NotNull(message = "业务主键ID不能为空")
    private String businessKey;

    /**
     * 发起人id
     */
    private String startUserId;

    /**
     * 发起人名称
     */
    @NotEmpty(message = "发起人名称不能为空")
    private String startUserName;

    /**
     * 发起人角色id
     */
    private String startUserRoleId;

    /**
     * 是否跳过第一步
     */
    private Boolean skipFirstStep = false;
}
