package com.bosen.camunda.vo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 任务明细
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/26
 */
@Data
public class ProcessTaskDetailVO implements Serializable {
    private static final long serialVersionUID = 5185973662202206967L;

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 标题
     */
    private String title;

    /**
     * 发起人名称
     */
    private String initiatorName;

    private String createTime;

    public ProcessTaskDetailVO() {
    }

    public ProcessTaskDetailVO(String taskId, String title, String initiatorName, String createTime) {
        this.taskId = taskId;
        this.title = title;
        this.initiatorName = initiatorName;
        this.createTime = createTime;
    }
}
