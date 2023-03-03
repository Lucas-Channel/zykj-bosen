package com.bosen.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.api.ClientDetail;

/**
 * @Author: Lucas
 */
public interface IClientService extends IService<ClientDetail> {
    ResponseData<Void> upsertClient(ClientDetail clientDetail);
}
