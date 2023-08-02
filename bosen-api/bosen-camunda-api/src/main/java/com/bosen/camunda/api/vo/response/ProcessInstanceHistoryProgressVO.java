package com.bosen.camunda.api.vo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询流程实例进度
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/1
 */
@Data
public class ProcessInstanceHistoryProgressVO implements Serializable {
    private static final long serialVersionUID = -7995520258109625684L;

    private String activityName;

    private Boolean finishFlag = false;

    private String auditUser;
}
