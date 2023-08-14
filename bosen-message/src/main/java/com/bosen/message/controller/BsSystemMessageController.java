package com.bosen.message.controller;


import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.service.IBsSystemMessageService;
import com.bosen.message.vo.request.SystemMessageQueryVO;
import com.bosen.message.vo.request.SystemMessageUpsertVO;
import com.bosen.message.vo.response.BsSystemMessageDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 系统消息(BsSystemMessage)表控制层
 *
 * @author Lucas
 * @since 2023-08-14 11:52:23
 */
@RestController
@RequestMapping("bsSystemMessage")
public class BsSystemMessageController {
    /**
     * 服务对象
     */
    @Resource
    private IBsSystemMessageService bsSystemMessageService;

    /**
     * 分页查询所有数据
     *
     * @param queryVO 分页对象
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public ResponseData<PageData<BsSystemMessageDetailVO>> pageList(SystemMessageQueryVO queryVO) {
        return bsSystemMessageService.pageList(queryVO);
    }

    /**
     * 新增/修改数据
     *
     * @param systemMessageUpsertVO 实体对象
     * @return 新增结果
     */
    @PostMapping("upsert")
    public ResponseData<Void> upsert(@RequestBody @Valid SystemMessageUpsertVO systemMessageUpsertVO) {
        return bsSystemMessageService.upsert(systemMessageUpsertVO);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody List<String> idList) {
        return ResponseData.judge(this.bsSystemMessageService.removeByIds(idList));
    }
}

