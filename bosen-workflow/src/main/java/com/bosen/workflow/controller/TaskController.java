package com.bosen.workflow.controller;

import com.bosen.common.constant.response.ResponseData;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 任务相关接口
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/19
 */
@RestController
@RequestMapping("/work/flow/task")
public class TaskController {

    @Resource
    private TaskRuntime taskRuntime;

    /**
     * 获取当前登录用户任务
     * @return 结果
     */
    @GetMapping("/listMyTasks")
    public ResponseData<Void> listMyTasks() {
        Page<Task> tasks = taskRuntime.tasks(Pageable.of(0, 100));
        List<Task> list=tasks.getContent();
        return ResponseData.success();
    }

    /**
     * 完成任务
     * @param taskId 任务id
     * @return 结果
     */
    @PostMapping("/completeTask")
    public ResponseData<Void> completeTask(@RequestBody String taskId) {
        Task task = taskRuntime.task(taskId);
        if(task.getAssignee() == null){
            // 拾取任务
            taskRuntime.claim(TaskPayloadBuilder.claim()
                    .withTaskId(task.getId())
                    .build());
        }
        taskRuntime.complete(TaskPayloadBuilder
                .complete()
                .withTaskId(task.getId())
                .build());
        return ResponseData.success();
    }
}
