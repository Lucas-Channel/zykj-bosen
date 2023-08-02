package com.bosen.camunda.api.feign;

import com.bosen.camunda.api.fallback.CamundaFeignServiceFallBack;
import com.bosen.camunda.api.vo.request.*;
import com.bosen.camunda.api.vo.response.ProcessDefinitionDetailVO;
import com.bosen.camunda.api.vo.response.ProcessInstanceHistoryProgressVO;
import com.bosen.camunda.api.vo.response.ProcessTaskDetailVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.vo.request.ApproveInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/2
 */
@FeignClient(value = "bosen-workflow", fallback = CamundaFeignServiceFallBack.class)
public interface ICamundaFeignService {

    /**
     * 分页查询流程定义
     * @param queryVO 查询参数
     * @return 结果
     */
    @GetMapping("/work/flow/processes/query/pageProcessDefinitions")
    ResponseData<PageData<ProcessDefinitionDetailVO>> pageProcessDefinitions(ProcessDefinitionQueryVO queryVO);

    /**
     * 查询我的待办任务
     * @return 列表
     */
    @GetMapping("/work/flow/processes/query/queryMyTasksToDo")
    ResponseData<List<ProcessTaskDetailVO>> queryMyTasksToDo(String title, String initiatorName);

    /**
     * 任务审批池-候选人
     * @return 结果
     */
    @GetMapping("/work/flow/processes/query/listWaitClaimTask")
    ResponseData<List<ProcessTaskDetailVO>> listWaitClaimTask();

    /**
     * 获取当前流程实例的进程
     * @param processInstanceId 流程实例id
     * @return 结果
     */
    @GetMapping("/work/flow/processes/query/listProcessInstanceProgress")
    ResponseData<List<ProcessInstanceHistoryProgressVO>> listProcessInstanceProgress(String processInstanceId);

    /**
     * 挂起或激活流程定义对象
     * @param suspendedProcessDefinitionVO 参数
     * @return 结果
     */
    @PostMapping("/work/flow/task/suspendedProcessDefinition")
    ResponseData<Void> suspendedProcessDefinition(@RequestBody @Valid SuspendedProcessDefinitionVO suspendedProcessDefinitionVO);

    /**
     * 启动流程
     * @param taskVO 参数
     * @return 结果
     */
    @PostMapping("/work/flow/task/startProcessByProcessKeyAndBusinessKey")
    ResponseData<Map<String, Object>> startProcessByProcessKeyAndBusinessKey(@RequestBody @Valid ProcessStartTaskVO taskVO);

    /**
     * 领取任务
     * @param taskReqVO 任务参数
     * @return 结果
     */
    @PostMapping("/work/flow/task/claimTask")
    ResponseData<Void> claimTask(@RequestBody @Valid ClaimTaskReqVO taskReqVO);

    /**
     * 退还/转交任务
     * @param taskReqVO 任务参数
     * @return 结果
     */
    @PostMapping("/work/flow/task/unClaimTaskAndTransfer")
    ResponseData<Void> unClaimTaskAndTransfer(@RequestBody @Valid ClaimTaskReqVO taskReqVO);

    /**
     * 审批
     * @param approveInfoVO 参数
     * @return 结果
     */
    @PostMapping("/work/flow/task/audit")
    ResponseData<Map<String, Object>> audit(@RequestBody @Valid ApproveInfoVO approveInfoVO);

    /**
     * 新增camunda用户
     * @param upsertVO 参数
     * @return 结果
     */
    @PostMapping("/work/flow/user/upsertCamundaUser")
    ResponseData<Void> upsertCamundaUser(@RequestBody @Valid CamundaUserUpsertVO upsertVO);
}
