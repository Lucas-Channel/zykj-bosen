package com.bosen.message.service;


import com.bosen.common.constant.response.ResponseData;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/18
 */
public interface IMQMessageService {
    ResponseData<Void> sendMsg(Object msg, String bindingName);

    ResponseData<Void> sendDelayMsg(Object msg, String bindingName, Integer seconds);
}
