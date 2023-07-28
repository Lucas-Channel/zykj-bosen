package com.bosen.camunda.controller;

import com.bosen.camunda.service.ICamundaUserService;
import com.bosen.camunda.vo.request.CamundaUserUpsertVO;
import com.bosen.common.constant.response.ResponseData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/28
 */
@RestController
@RequestMapping("/work/flow/user")
public class CamundaUserController {

    @Resource
    private ICamundaUserService camundaUserService;

    @PostMapping("/upsertCamundaUser")
    public ResponseData<Void> upsertCamundaUser(@RequestBody @Valid CamundaUserUpsertVO upsertVO) {
        return camundaUserService.upsertCamundaUser(upsertVO);
    }
}
