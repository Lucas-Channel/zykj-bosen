package com.bosen.camunda.api.constant;

/**
 * 工作流实例相关key
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/26
 */
public class CamundaProcessInstanceTitleConstant {

    /**
     * 流程实例标题
     */
    public static final String TITLE_KEY = "title";
    /**
     * 第一个参数为单据号，第二个为业务流程名，第三个参数为日期,第四个参数为内容简要
     */
    public static final String TITLE_VAL = "%s-%s-%s-%s";

    /**
     * 发起人
     */
    public static final String INITIATOR_KEY = "initiator";

    /**
     * 流程跳转参数的名称
     */
    public static final String TASK_JUMP_VAR_KEY = "agree";

    /**
     * 任务同意
     */
    public static final String TASK_PASS_VAR = "1";

    /**
     * 任务不同意
     */
    public static final String TASK_NO_PASS_VAR = "0";
}
