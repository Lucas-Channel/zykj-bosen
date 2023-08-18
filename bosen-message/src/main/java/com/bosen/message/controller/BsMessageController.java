package com.bosen.message.controller;


import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.api.vo.request.MessageUpsertVO;
import com.bosen.message.service.IBsMessageService;
import com.bosen.message.vo.request.MessageQueryVO;
import com.bosen.message.vo.response.BsMessageDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 消息(BsMessage)表控制层
 *
 * @author Lucas
 * @since 2023-08-18 11:14:55
 */
@RestController
@RequestMapping("/common/message")
public class BsMessageController {
    /**
     * 服务对象
     */
    @Resource
    private IBsMessageService bsMessageService;

    /**
     * 分页查询用户消息所有数据
     *
     * @param pageVO 分页对象
     * @return 所有数据
     */
    @GetMapping("/pageUserMessageList")
    public ResponseData<PageData<BsMessageDetailVO>> pageUserMessageList(MessageQueryVO pageVO) {
        return bsMessageService.pageUserMessageList(pageVO);
    }

    /**
     * 后台分页查询所有系统消息数据
     *
     * @param queryVO 分页对象
     * @return 所有数据
     */
    @GetMapping("/pageSystemMessageList")
    public ResponseData<PageData<BsMessageDetailVO>> pageSystemMessageList(MessageQueryVO queryVO) {
        return bsMessageService.pageSystemMessageList(queryVO);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail/{id}")
    public ResponseData<BsMessageDetailVO> selectOne(@PathVariable("id") String id) {
        return this.bsMessageService.getDetail(id);
    }

    /**
     * 新增/修改数据
     *
     * @param messageUpsertVO 实体对象
     * @return 新增结果
     */
    @PostMapping("upsert")
    public ResponseData<Void> upsert(@RequestBody @Valid MessageUpsertVO messageUpsertVO) {
        return bsMessageService.upsert(messageUpsertVO);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody List<String> idList) {
        return ResponseData.judge(this.bsMessageService.removeByIds(idList));
    }
}

