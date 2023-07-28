package com.bosen.camunda.controller;

import com.bosen.camunda.service.IProcessService;
import com.bosen.camunda.vo.request.ProcessDefinitionQueryVO;
import com.bosen.camunda.vo.response.ProcessDefinitionDetailVO;
import com.bosen.camunda.vo.response.ProcessTaskDetailVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 流程查询相关接口
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/19
 */
@RestController
@RequestMapping("/work/flow/processes/query")
public class ProcessesQueryController {
    @Resource
    private IProcessService processService;

    /**
     * 分页查询流程定义
     * @param queryVO 查询参数
     * @return 结果
     */
    @GetMapping("/pageProcessDefinitions")
    public ResponseData<PageData<ProcessDefinitionDetailVO>> pageProcessDefinitions(ProcessDefinitionQueryVO queryVO) {
        return ResponseData.success(processService.pageProcessDefinition(queryVO));
    }

    /**
     * 查询我的待办任务
     * @return 列表
     */
    @GetMapping("/queryMyTasksToDo")
    public ResponseData<List<ProcessTaskDetailVO>> queryMyTasksToDo(String title, String initiatorName) {
        return processService.queryMyTasksToDo(title, initiatorName);
    }

    /**
     * 任务审批池-候选人
     * @return 结果
     */
    @GetMapping("/listWaitClaimTask")
    public ResponseData<List<ProcessTaskDetailVO>> listWaitClaimTask() {
        return processService.listWaitClaimTask();
    }

}
