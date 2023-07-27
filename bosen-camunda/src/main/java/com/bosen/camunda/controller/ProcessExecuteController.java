package com.bosen.camunda.controller;

import com.bosen.camunda.api.vo.request.ProcessStartTaskVO;
import com.bosen.camunda.service.IProcessService;
import com.bosen.common.constant.response.ResponseData;
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
     * 启动流程
     * @param taskVO 参数
     * @return 结果
     */
    @PostMapping("/startProcessByProcessKeyAndBusinessKey")
    public ResponseData<Map<String, Object>> startProcessByProcessKeyAndBusinessKey(@RequestBody @Valid ProcessStartTaskVO taskVO) {
        return processService.startProcessByProcessKeyAndBusinessKey(taskVO);
    }


}
