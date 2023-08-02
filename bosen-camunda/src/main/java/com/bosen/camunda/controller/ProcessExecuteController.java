package com.bosen.camunda.controller;

import com.bosen.camunda.api.vo.request.ProcessStartTaskVO;
import com.bosen.camunda.service.IProcessService;
import com.bosen.camunda.api.vo.request.ClaimTaskReqVO;
import com.bosen.camunda.api.vo.request.SuspendedProcessDefinitionVO;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.vo.request.ApproveInfoVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * 流程执行相关接口
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/19
 */
@RestController
@RequestMapping("/work/flow/task")
public class ProcessExecuteController {

    @Resource
    private IProcessService processService;

    /**
     * 挂起或激活流程定义对象
     * @param suspendedProcessDefinitionVO 参数
     * @return 结果
     */
    @PostMapping("/suspendedProcessDefinition")
    public ResponseData<Void> suspendedProcessDefinition(@RequestBody @Valid SuspendedProcessDefinitionVO suspendedProcessDefinitionVO) {
        return processService.suspendedProcessDefinition(suspendedProcessDefinitionVO);
    }

    /**
     * 启动流程
     * @param taskVO 参数
     * @return 结果
     */
    @PostMapping("/startProcessByProcessKeyAndBusinessKey")
    public ResponseData<Map<String, Object>> startProcessByProcessKeyAndBusinessKey(@RequestBody @Valid ProcessStartTaskVO taskVO) {
        return processService.startProcessByProcessKeyAndBusinessKey(taskVO);
    }

    /**
     * 领取任务
     * @param taskReqVO 任务参数
     * @return 结果
     */
    @PostMapping("/claimTask")
    public ResponseData<Void> claimTask(@RequestBody @Valid ClaimTaskReqVO taskReqVO) {
        return processService.claimTask(taskReqVO);
    }

    /**
     * 退还/转交任务
     * @param taskReqVO 任务参数
     * @return 结果
     */
    @PostMapping("/unClaimTaskAndTransfer")
    public ResponseData<Void> unClaimTaskAndTransfer(@RequestBody @Valid ClaimTaskReqVO taskReqVO) {
        return processService.unClaimTaskAndTransfer(taskReqVO);
    }

    /**
     * 审批
     * @param approveInfoVO 参数
     * @return 结果
     */
    @PostMapping("/audit")
    public ResponseData<Map<String, Object>> audit(@RequestBody @Valid ApproveInfoVO approveInfoVO) {
        return processService.audit(approveInfoVO);
    }
}
