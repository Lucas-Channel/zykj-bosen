package com.bosen.message.controller;


import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.service.IBsMessageReadService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 消息已读列表(BsMessageRead)表控制层
 *
 * @author Lucas
 * @since 2023-08-18 11:14:56
 */
@RestController
@RequestMapping("/message/read")
public class BsMessageReadController {
    /**
     * 服务对象
     */
    @Resource
    private IBsMessageReadService bsMessageReadService;


    /**
     * 已读
     * @param messageId 消息id
     * @return 结果
     */
    @PostMapping("/read")
    public ResponseData<Void> ReadMsg(@RequestBody String messageId) {
        return bsMessageReadService.ReadMsg(messageId);
    }

    /**
     * 全部已读
     * @return 结果
     */
    @PostMapping("/allReadMsg")
    public ResponseData<Void> allReadMsg(@RequestBody List<String> ids) {
        return bsMessageReadService.allReadMsg(ids);
    }
}

