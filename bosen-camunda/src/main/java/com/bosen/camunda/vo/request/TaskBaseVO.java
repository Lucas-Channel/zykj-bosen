package com.bosen.camunda.vo.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 任务基础对象
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/28
 */
@Data
public class TaskBaseVO implements Serializable {
    private static final long serialVersionUID = -1119402373721993165L;

    private String taskId;
}
