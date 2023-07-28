package com.bosen.camunda.vo.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 领取任务
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ClaimTaskReqVO extends TaskBaseVO implements Serializable {
    private static final long serialVersionUID = 701584287823538722L;

    /**
     * 是否审批
     */
    private Boolean auditFlag = false;

    private String transferUserId;
}
