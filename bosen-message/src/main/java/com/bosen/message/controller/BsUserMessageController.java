package com.bosen.message.controller;


import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.service.IBsUserMessageService;
import com.bosen.message.vo.request.UserMessageQueryVO;
import com.bosen.message.vo.request.UserMessageUpsertVO;
import com.bosen.message.vo.response.BsUserMessageDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 用户消息(BsUserMessage)表控制层
 *
 * @author Lucas
 * @since 2023-08-14 15:26:30
 */
@RestController
@RequestMapping("bsUserMessage")
public class BsUserMessageController {
    /**
     * 服务对象
     */
    @Resource
    private IBsUserMessageService bsUserMessageService;

    /**
     * 分页查询所有数据
     *
     * @param queryVO 分页对象
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public ResponseData<PageData<BsUserMessageDetailVO>> pageList(UserMessageQueryVO queryVO) {
        return bsUserMessageService.pageList(queryVO);
    }

    /**
     * 新增/修改数据
     *
     * @param messageUpsertVO 实体对象
     * @return 新增结果
     */
    @PostMapping("upsert")
    public ResponseData<Void> upsert(@RequestBody @Valid UserMessageUpsertVO messageUpsertVO) {
        return bsUserMessageService.upsert(messageUpsertVO);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody List<String> idList) {
        return ResponseData.judge(this.bsUserMessageService.removeByIds(idList));
    }
}

