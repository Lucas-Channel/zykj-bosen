package com.bosen.message.api.fallback;

import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.api.feign.IMessageFeign;
import com.bosen.message.api.vo.request.SendWsMessageBatchVO;
import com.bosen.message.api.vo.request.SendWsMessageVO;
import com.bosen.message.api.vo.request.UserMessageUpsertVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/2
 */
@Slf4j
@Component
public class MessageFeignFallBack implements IMessageFeign {

    @Override
    public ResponseData<Void> sendWsMessage(SendWsMessageVO wsMessageVO) {
        return ResponseData.fail(ResponseCode.MESSAGE_SERVER_ERROR);
    }

    @Override
    public ResponseData<Void> sendWsMessageBatch(SendWsMessageBatchVO wsMessageVO) {
        return ResponseData.fail(ResponseCode.MESSAGE_SERVER_ERROR);
    }

    @Override
    public ResponseData<Void> upsert(UserMessageUpsertVO messageUpsertVO) {
        return ResponseData.fail(ResponseCode.MESSAGE_SERVER_ERROR);
    }
}
