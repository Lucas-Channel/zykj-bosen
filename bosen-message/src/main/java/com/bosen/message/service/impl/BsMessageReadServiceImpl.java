package com.bosen.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.domain.BsMessageRead;
import com.bosen.message.mapper.BsMessageReadMapper;
import com.bosen.message.service.IBsMessageReadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 消息已读列表(BsMessageRead)表服务实现类
 *
 * @author Lucas
 * @since 2023-08-18 11:27:25
 */
@Service
public class BsMessageReadServiceImpl extends ServiceImpl<BsMessageReadMapper, BsMessageRead> implements IBsMessageReadService {

    @Override
    public ResponseData<Void> ReadMsg(String messageId) {
        return ResponseData.judge(this.save(this.setBaseMsg(messageId)));
    }

    private BsMessageRead setBaseMsg(String messageId) {
        // todo 登入信息替换
        BsMessageRead messageRead = new BsMessageRead();
        messageRead.setMessageId(messageId);
        messageRead.setReceiveUserId("1");
        messageRead.setReceiveUserRoleId("1");
        return messageRead;
    }

    @Override
    @Transactional
    public ResponseData<Void> allReadMsg(@RequestBody List<String> ids) {
        return ResponseData.judge(this.saveBatch(ids.stream().map(this::setBaseMsg).collect(Collectors.toList())));
    }

}

