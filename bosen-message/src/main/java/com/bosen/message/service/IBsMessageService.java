package com.bosen.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.api.vo.request.MessageUpsertVO;
import com.bosen.message.domain.BsMessage;
import com.bosen.message.vo.request.MessageQueryVO;
import com.bosen.message.vo.response.BsMessageDetailVO;

/**
 * 消息(BsMessage)表服务接口
 *
 * @author Lucas
 * @since 2023-08-18 11:14:56
 */
public interface IBsMessageService extends IService<BsMessage> {
    ResponseData<Void> upsert(MessageUpsertVO messageUpsertVO);

    ResponseData<BsMessageDetailVO> getDetail(String id);

    ResponseData<PageData<BsMessageDetailVO>> pageUserMessageList(MessageQueryVO pageVO);

    ResponseData<PageData<BsMessageDetailVO>> pageSystemMessageList(MessageQueryVO queryVO);

    ResponseData<Integer> getUnreadCount(String userId, String roleId);
}

