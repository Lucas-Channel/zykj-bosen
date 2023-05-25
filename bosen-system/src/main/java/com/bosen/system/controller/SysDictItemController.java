package com.bosen.system.controller;


import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.system.service.ISysDictItemService;
import com.bosen.system.vo.request.DictItemQueryVO;
import com.bosen.system.vo.request.DictItemUpsertVO;
import com.bosen.system.vo.response.DictItemDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 字典管理明细(BsSysDictItem)表控制层
 *
 * @author Lucas
 * @since 2023-05-25 16:34:05
 */
@RestController
@RequestMapping("/sys/dict/item")
public class SysDictItemController {
    /**
     * 服务对象
     */
    @Resource
    private ISysDictItemService sysDictItemService;

    /**
     * 分页查询所有数据
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public ResponseData<PageData<DictItemDetailVO>> pageList(DictItemQueryVO pageVO) {
        return sysDictItemService.pageList(pageVO);
    }

    /**
     * 新增/修改数据
     * @return 新增结果
     */
    @PostMapping("upsert")
    public ResponseData<Void> upsert(@RequestBody @Valid DictItemUpsertVO dictItemUpsertVO) {
        return sysDictItemService.upsert(dictItemUpsertVO);
    }

    /**
     * 删除数据
     * @return 删除结果
     */
    @DeleteMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestParam("idList") List<String> idList) {
        return ResponseData.judge(this.sysDictItemService.removeByIds(idList));
    }
}

