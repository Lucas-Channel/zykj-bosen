package com.bosen.workflow.controller;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 流程相关接口
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/19
 */
@RestController
@RequestMapping("/work/flow/processes")
public class ProcessesController {
    @Resource
    private RuntimeService runtimeService;
//
//    @GetMapping("/pageProcessDefinitions")
//    public ResponseData<PageData<ProcessDefinition>> pageProcessDefinitions() {
//        Page<ProcessDefinition> page = processRuntime.processDefinitions(Pageable.of(0, 10));
//        System.out.println("我读取到了好多好多工作流" + page.getTotalItems());
//        return ResponseData.success(new PageData<>((long) page.getTotalItems(), page.getContent()));
//    }
//
//    /**
//     * 获取流程实例
//     */
//    @GetMapping("pageProcessInstance")
//    public void pageProcessInstance() {
//        Page<ProcessInstance> processInstancePage = processRuntime
//                .processInstances(Pageable.of(0,100));
//        System.out.println("流程实例数量："+processInstancePage.getTotalItems());
//        List<org.activiti.engine.runtime.ProcessInstance> list1 = runtimeService.createProcessInstanceQuery().list();
//        List<ProcessInstance> list = processInstancePage.getContent();
//        for(ProcessInstance pi : list){
//            System.out.println("-----------------------");
//            System.out.println("getId：" + pi.getId());
//            System.out.println("getName：" + pi.getName());
//            System.out.println("getStartDate：" + pi.getStartDate());
//            System.out.println("getStatus：" + pi.getStatus());
//            System.out.println("getProcessDefinitionId：" + pi.getProcessDefinitionId());
//            System.out.println("getProcessDefinitionKey：" + pi.getProcessDefinitionKey());
//
//        }
//    }
//
//    /**
//     * 启动流程实例
//     */
//    @PostMapping("/startProcessInstance")
//    public void startProcessInstance() {
//        ProcessInstance processInstance = processRuntime.start(ProcessPayloadBuilder
//                .start()
//                .withProcessDefinitionKey("Test2")
//                .withName("我在做测试")
//                .withVariable("status","1")//启动加参数
//                .withBusinessKey("1")
//                .build()
//        );
//        System.out.println("流程实例：" + processInstance.getId());
//    }
//
//    /**
//     * 删除流程实例
//     */
//    @GetMapping("/delProcessInstance")
//    public void delProcessInstance(String processInstanceId) {
//        ProcessInstance processInstance = processRuntime.delete(ProcessPayloadBuilder
//                .delete()
//                .withProcessInstanceId(processInstanceId)
//                .build()
//        );
//    }
//
//    /**
//     * 挂起流程实例
//     * @param processInstanceId 流程实例ID
//     */
//    @GetMapping("/suspendProcessInstance")
//    public void suspendProcessInstance(String processInstanceId) {
//        ProcessInstance processInstance = processRuntime.suspend(ProcessPayloadBuilder
//                .suspend()
//                .withProcessInstanceId(processInstanceId)
//                .build()
//        );
//    }
//
//    /**
//     * 激活流程实例
//     */
//    @GetMapping("/resumeProcessInstance")
//    public void resumeProcessInstance(String processInstanceId) {
//        ProcessInstance processInstance = processRuntime.resume(ProcessPayloadBuilder
//                .resume()
//                .withProcessInstanceId(processInstanceId)
//                .build()
//        );
//    }
//
//
//    /**
//     * 获取流程实例参数
//     */
//    @GetMapping("/getVariables")
//    public void getVariables(String processInstanceId) {
//        List<VariableInstance> list = processRuntime.variables(ProcessPayloadBuilder
//                .variables()
//                .withProcessInstanceId(processInstanceId)
//                .build()
//        );
//        for(VariableInstance vi : list){
//            System.out.println("-------------------");
//            System.out.println("getName：" + vi.getName());
//            System.out.println("getValue：" + vi.getValue());
//            System.out.println("getTaskId：" + vi.getTaskId());
//            System.out.println("getProcessInstanceId：" + vi.getProcessInstanceId());
//        }
//    }
}
