package com.bosen.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.domain.BsMessageRead;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 消息已读列表(BsMessageRead)表服务接口
 *
 * @author Lucas
 * @since 2023-08-18 11:27:23
 */
public interface IBsMessageReadService extends IService<BsMessageRead> {
    ResponseData<Void> ReadMsg(String messageId);

    ResponseData<Void> allReadMsg(@RequestBody List<String> ids);

}

