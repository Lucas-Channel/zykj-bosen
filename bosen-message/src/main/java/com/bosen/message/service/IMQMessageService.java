package com.bosen.message.service;


import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.api.vo.request.SendMQMessageVO;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/18
 */
public interface IMQMessageService {
    ResponseData<Void> sendMsg(SendMQMessageVO mqMessageVO);

    ResponseData<Void> sendDelayMsg(SendMQMessageVO mqMessageVO);
}
