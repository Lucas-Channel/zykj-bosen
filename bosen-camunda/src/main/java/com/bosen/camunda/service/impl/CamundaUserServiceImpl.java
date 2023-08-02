package com.bosen.camunda.service.impl;

import com.bosen.camunda.service.ICamundaUserService;
import com.bosen.camunda.api.vo.request.CamundaUserUpsertVO;
import com.bosen.common.constant.response.ResponseData;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/28
 */
@Service
public class CamundaUserServiceImpl implements ICamundaUserService {

    @Resource
    private IdentityService identityService;

    @Override
    public ResponseData<Void> upsertCamundaUser(CamundaUserUpsertVO upsertVO) {
        User user = identityService.createUserQuery().userId(upsertVO.getUserId()).singleResult();
        if (Objects.isNull(user)) {
            user = identityService.newUser(upsertVO.getUserId());
        }
        user.setPassword(upsertVO.getPassword());
        user.setFirstName(upsertVO.getFirstName());
        user.setLastName(upsertVO.getLastName());
        identityService.saveUser(user);
        return ResponseData.success();
    }
}
