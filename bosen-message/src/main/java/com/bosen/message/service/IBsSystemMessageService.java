package com.bosen.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.domain.BsSystemMessage;
import com.bosen.message.vo.request.SystemMessageQueryVO;
import com.bosen.message.vo.request.SystemMessageUpsertVO;
import com.bosen.message.vo.response.BsSystemMessageDetailVO;

/**
 * 系统消息(BsSystemMessage)表服务接口
 *
 * @author Lucas
 * @since 2023-08-14 11:52:29
 */
public interface IBsSystemMessageService extends IService<BsSystemMessage> {
    ResponseData<PageData<BsSystemMessageDetailVO>> pageList(SystemMessageQueryVO queryVO);

    ResponseData<Void> upsert(SystemMessageUpsertVO systemMessageUpsertVO);
}

