package com.bosen.camunda.service.impl;

import com.bosen.camunda.api.constant.CamundaProcessInstanceTitleConstant;
import com.bosen.camunda.api.vo.request.ProcessStartTaskVO;
import com.bosen.camunda.service.IProcessService;
import com.bosen.camunda.vo.request.ClaimTaskReqVO;
import com.bosen.camunda.vo.request.ProcessDefinitionQueryVO;
import com.bosen.camunda.vo.request.SuspendedProcessDefinitionVO;
import com.bosen.camunda.vo.response.ProcessDefinitionDetailVO;
import com.bosen.camunda.vo.response.ProcessTaskDetailVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.common.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.repository.ProcessDefinitionQuery;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 工作流业务层
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/20
 */
@Slf4j
@Service
public class ProcessServiceImpl implements IProcessService {
    @Resource
    private TaskService taskService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private RepositoryService repositoryService;

    @Override
    public PageData<ProcessDefinitionDetailVO> pageProcessDefinition(ProcessDefinitionQueryVO queryVO) {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        if (StringUtils.hasLength(queryVO.getProcessName())) {
            processDefinitionQuery.processDefinitionNameLike(queryVO.getProcessName());
        }
        if (StringUtils.hasLength(queryVO.getProcessKey())) {
            processDefinitionQuery.processDefinitionKeyLike(queryVO.getProcessKey());
        }
        processDefinitionQuery.latestVersion();
        processDefinitionQuery.orderByProcessDefinitionId().desc();
        // 特别说明：第一个参数为查询数据的起始下标，第二个参数为页面大小
        processDefinitionQuery.listPage((queryVO.getCurrent() - 1) * queryVO.getSize(), queryVO.getSize());
        long count = processDefinitionQuery.count();
        List<ProcessDefinition> list = processDefinitionQuery.list();
        return new PageData<>(count, list.stream().map(i -> {
            ProcessDefinitionDetailVO detailVO = new ProcessDefinitionDetailVO();
            detailVO.setId(i.getId());
            detailVO.setProcessDefinitionKey(i.getKey());
            detailVO.setProcessDefinitionName(i.getName());
            detailVO.setResourceName(i.getResourceName());
            detailVO.setHasStartFormKey(i.hasStartFormKey());
            detailVO.setStartTableInTaskList(i.isStartableInTasklist());
            detailVO.setSuspended(i.isSuspended());
            return detailVO;
        }).collect(Collectors.toList()));
    }

    @Override
    public ResponseData<List<ProcessTaskDetailVO>> queryMyTasksToDo(String title, String initiatorName) {
        TaskQuery taskQuery = taskService.createTaskQuery().active()
                // todo 切换为当前登录人
                .taskAssignee("lucas");
        if (StringUtils.hasLength(title)) {
            taskQuery.processVariableValueLike(CamundaProcessInstanceTitleConstant.TITLE_KEY, title);
        }
        if (StringUtils.hasLength(initiatorName)) {
            taskQuery.processVariableValueLike(CamundaProcessInstanceTitleConstant.INITIATOR_KEY, initiatorName);
        }
        List<Task> list = taskQuery.list();
        return ResponseData.success(list.stream().map(i -> {
            Map<String, Object> variables = runtimeService.getVariables(i.getExecutionId());
            String titleVar = variables.get(CamundaProcessInstanceTitleConstant.TITLE_KEY).toString();
            String initiator = variables.get(CamundaProcessInstanceTitleConstant.INITIATOR_KEY).toString();
            return new ProcessTaskDetailVO(i.getId(), titleVar, initiator, DateFormat.getDateInstance().format(i.getCreateTime()));
        }).collect(Collectors.toList()));
    }

    @Override
    public ResponseData<Map<String, Object>> startProcessByProcessKeyAndBusinessKey(ProcessStartTaskVO taskVO) {
        // step1 查询是否存在当前流程实例key
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(taskVO.getProcessKey()).active().latestVersion().singleResult();
        if (Objects.isNull(processDefinition)) {
            return ResponseData.fail(ResponseCode.PROCESS_KEY_NOT_EXIT_ERROR);
        }
        VariableMap variableMap = Variables.putValue(CamundaProcessInstanceTitleConstant.TITLE_KEY, String.format(CamundaProcessInstanceTitleConstant.TITLE_VAL, "单据号", "业务流程名称", DateTimeUtil.formatDateTime(LocalDateTime.now()), "这个是备注"));
        variableMap.putValue(CamundaProcessInstanceTitleConstant.INITIATOR_KEY, taskVO.getStartUserName());
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(taskVO.getProcessKey(), taskVO.getBusinessKey(), variableMap);
        Map<String, Object> objectMap = new HashMap<>();
        if (taskVO.getSkipFirstStep()) {
            Task task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).active().singleResult();
            Map<String, Object> map = new HashMap<>();
            map.put(CamundaProcessInstanceTitleConstant.TASK_JUMP_VAR_KEY, 1);
            taskService.complete(task.getId(), map);
            BpmnModelInstance model = repositoryService.getBpmnModelInstance(processDefinition.getId());
            UserTask userTask = model.getModelElementById(task.getTaskDefinitionKey());
            Collection<SequenceFlow> outgoing = userTask.getSucceedingNodes().singleResult().getOutgoing();
            for (SequenceFlow flow : outgoing) {
                Collection<CamundaProperty> properties = flow.getExtensionElements().getElementsQuery().filterByType(CamundaProperties.class).singleResult().getCamundaProperties();
                if (properties.stream().noneMatch(i -> Objects.equals(i.getCamundaName(), CamundaProcessInstanceTitleConstant.TASK_JUMP_VAR_KEY) && Objects.equals(i.getCamundaValue(), CamundaProcessInstanceTitleConstant.TASK_PASS_VAR))) {
                    continue;
                }
                properties.forEach(i -> objectMap.put(i.getCamundaName(), i.getCamundaValue()));
            }
        }
        return ResponseData.success(objectMap);
    }

    @Override
    public ResponseData<List<ProcessTaskDetailVO>> listWaitClaimTask() {
        TaskQuery taskQuery = taskService.createTaskQuery().active()
                // todo 切换为当前登录人
                .taskCandidateUser("lucas");
        List<Task> list = taskQuery.list();
        return ResponseData.success(list.stream().map(i -> {
            Map<String, Object> variables = runtimeService.getVariables(i.getExecutionId());
            String titleVar = variables.get(CamundaProcessInstanceTitleConstant.TITLE_KEY).toString();
            String initiator = variables.get(CamundaProcessInstanceTitleConstant.INITIATOR_KEY).toString();
            return new ProcessTaskDetailVO(i.getId(), titleVar, initiator, DateFormat.getDateInstance().format(i.getCreateTime()));
        }).collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public ResponseData<Void> claimTask(ClaimTaskReqVO taskReqVO) {
        Task task = taskService.createTaskQuery().active().taskId(taskReqVO.getTaskId()).taskUnassigned().taskCandidateUser("lucas").singleResult();
        if (Objects.isNull(task)) {
            throw new BusinessException(ResponseCode.PROCESS_TASK_NOT_EXIT_ERROR);
        }
        taskService.claim(taskReqVO.getTaskId(), "lucas");
        if (taskReqVO.getAuditFlag()) {
            Map<String, Object> map = new HashMap<>();
            map.put(CamundaProcessInstanceTitleConstant.TASK_JUMP_VAR_KEY, 1);
            taskService.complete(taskReqVO.getTaskId(), map);
        }
        return ResponseData.success();
    }

    @Override
    public ResponseData<Void> unClaimTaskAndTransfer(ClaimTaskReqVO taskReqVO) {
        Task task = taskService.createTaskQuery().active().taskId(taskReqVO.getTaskId()).taskAssignee("lucas").singleResult();
        if (Objects.isNull(task)) {
            throw new BusinessException(ResponseCode.PROCESS_TASK_NOT_EXIT_ERROR);
        }
        taskService.setAssignee(task.getId(),StringUtils.hasLength(taskReqVO.getTransferUserId()) ? taskReqVO.getTransferUserId() : null);
        return ResponseData.success();
    }

    @Override
    public ResponseData<Void> suspendedProcessDefinition(SuspendedProcessDefinitionVO suspendedProcessDefinitionVO) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(suspendedProcessDefinitionVO.getProcessDefinitionId()).singleResult();
        if (Objects.isNull(processDefinition)) {
            return ResponseData.fail(ResponseCode.PROCESS_KEY_NOT_EXIT_ERROR);
        }
        if (suspendedProcessDefinitionVO.getSuspended()) {
            repositoryService.suspendProcessDefinitionById(suspendedProcessDefinitionVO.getProcessDefinitionId(), suspendedProcessDefinitionVO.getIncludeProcessInstances(), new Date());
        } else {
            repositoryService.activateProcessDefinitionById(suspendedProcessDefinitionVO.getProcessDefinitionId(), suspendedProcessDefinitionVO.getIncludeProcessInstances(), new Date());
        }

        return ResponseData.success();
    }
}
