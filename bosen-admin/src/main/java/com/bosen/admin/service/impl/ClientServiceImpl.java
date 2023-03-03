package com.bosen.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.admin.mapper.ClientMapper;
import com.bosen.admin.service.IClientService;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.api.ClientDetail;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 客户端管理
 * @Author: Lucas
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, ClientDetail> implements IClientService {
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseData<Void> upsertClient(ClientDetail clientDetail) {
        if (StringUtils.hasLength(clientDetail.getClientSecret())) {
            clientDetail.setClientSecret(passwordEncoder.encode(clientDetail.getClientSecret()));
        }
        return ResponseData.judge(this.saveOrUpdate(clientDetail));
    }
}
