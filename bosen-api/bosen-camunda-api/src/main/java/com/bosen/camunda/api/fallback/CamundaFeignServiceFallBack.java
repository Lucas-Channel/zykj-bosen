package com.bosen.camunda.api.fallback;

import com.bosen.camunda.api.feign.ICamundaFeignService;
import com.bosen.camunda.api.vo.request.*;
import com.bosen.camunda.api.vo.response.ProcessDefinitionDetailVO;
import com.bosen.camunda.api.vo.response.ProcessInstanceHistoryProgressVO;
import com.bosen.camunda.api.vo.response.ProcessTaskDetailVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.vo.request.ApproveInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/2
 */
@Slf4j
@Component
public class CamundaFeignServiceFallBack implements ICamundaFeignService {
    @Override
    public ResponseData<PageData<ProcessDefinitionDetailVO>> pageProcessDefinitions(ProcessDefinitionQueryVO queryVO) {
        return ResponseData.fail(ResponseCode.CAMUNDA_SERVICE_ERROR);
    }

    @Override
    public ResponseData<List<ProcessTaskDetailVO>> queryMyTasksToDo(String title, String initiatorName) {
        return ResponseData.fail(ResponseCode.CAMUNDA_SERVICE_ERROR);
    }

    @Override
    public ResponseData<List<ProcessTaskDetailVO>> listWaitClaimTask() {
        return ResponseData.fail(ResponseCode.CAMUNDA_SERVICE_ERROR);
    }

    @Override
    public ResponseData<List<ProcessInstanceHistoryProgressVO>> listProcessInstanceProgress(String processInstanceId) {
        return ResponseData.fail(ResponseCode.CAMUNDA_SERVICE_ERROR);
    }

    @Override
    public ResponseData<Void> suspendedProcessDefinition(SuspendedProcessDefinitionVO suspendedProcessDefinitionVO) {
        return ResponseData.fail(ResponseCode.CAMUNDA_SERVICE_ERROR);
    }

    @Override
    public ResponseData<Map<String, Object>> startProcessByProcessKeyAndBusinessKey(ProcessStartTaskVO taskVO) {
        return ResponseData.fail(ResponseCode.CAMUNDA_SERVICE_ERROR);
    }

    @Override
    public ResponseData<Void> claimTask(ClaimTaskReqVO taskReqVO) {
        return ResponseData.fail(ResponseCode.CAMUNDA_SERVICE_ERROR);
    }

    @Override
    public ResponseData<Void> unClaimTaskAndTransfer(ClaimTaskReqVO taskReqVO) {
        return ResponseData.fail(ResponseCode.CAMUNDA_SERVICE_ERROR);
    }

    @Override
    public ResponseData<Map<String, Object>> audit(ApproveInfoVO approveInfoVO) {
        return ResponseData.fail(ResponseCode.CAMUNDA_SERVICE_ERROR);
    }

    @Override
    public ResponseData<Void> upsertCamundaUser(CamundaUserUpsertVO upsertVO) {
        return ResponseData.fail(ResponseCode.CAMUNDA_SERVICE_ERROR);
    }
}
