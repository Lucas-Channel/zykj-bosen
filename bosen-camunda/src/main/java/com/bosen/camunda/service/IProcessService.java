package com.bosen.camunda.service;

import com.bosen.camunda.api.vo.request.ProcessStartTaskVO;
import com.bosen.camunda.vo.request.ClaimTaskReqVO;
import com.bosen.camunda.vo.request.ProcessDefinitionQueryVO;
import com.bosen.camunda.vo.request.SuspendedProcessDefinitionVO;
import com.bosen.camunda.vo.response.ProcessDefinitionDetailVO;
import com.bosen.camunda.vo.response.ProcessTaskDetailVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.vo.request.ApproveInfoVO;

import java.util.List;
import java.util.Map;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/20
 */
public interface IProcessService {

    PageData<ProcessDefinitionDetailVO> pageProcessDefinition(ProcessDefinitionQueryVO queryVO);

    ResponseData<List<ProcessTaskDetailVO>> queryMyTasksToDo(String title, String initiatorName);

    ResponseData<Map<String, Object>> startProcessByProcessKeyAndBusinessKey(ProcessStartTaskVO taskVO);

    ResponseData<List<ProcessTaskDetailVO>> listWaitClaimTask();

    ResponseData<Void> claimTask(ClaimTaskReqVO taskReqVO);

    ResponseData<Void> unClaimTaskAndTransfer(ClaimTaskReqVO taskReqVO);

    ResponseData<Void> suspendedProcessDefinition(SuspendedProcessDefinitionVO suspendedProcessDefinitionVO);

    ResponseData<Map<String, Object>> audit(ApproveInfoVO approveInfoVO);
}
