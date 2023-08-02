package com.bosen.camunda.service;

import com.bosen.camunda.api.vo.request.CamundaUserUpsertVO;
import com.bosen.common.constant.response.ResponseData;

/**
 * camunda user 接口层
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/28
 */
public interface ICamundaUserService {

    ResponseData<Void> upsertCamundaUser(CamundaUserUpsertVO upsertVO);
}
