package com.bosen.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.api.vo.request.UserMessageUpsertVO;
import com.bosen.message.domain.BsUserMessage;
import com.bosen.message.vo.request.UserMessageQueryVO;
import com.bosen.message.vo.response.BsUserMessageDetailVO;

/**
 * 用户消息(BsUserMessage)表服务接口
 *
 * @author Lucas
 * @since 2023-08-14 15:26:30
 */
public interface IBsUserMessageService extends IService<BsUserMessage> {

    ResponseData<PageData<BsUserMessageDetailVO>> pageList(UserMessageQueryVO queryVO);

    ResponseData<Void> upsert(UserMessageUpsertVO messageUpsertVO);

    ResponseData<Void> ReadMsg(String id);

    ResponseData<Void> allReadMsg();

    ResponseData<Integer> getUnreadCount(String userId, String roleId);

}

