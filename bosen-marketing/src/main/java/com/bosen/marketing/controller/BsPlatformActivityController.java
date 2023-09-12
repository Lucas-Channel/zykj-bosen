package com.bosen.marketing.controller;


import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.PageVO;
import com.bosen.marketing.domain.BsPlatformActivity;
import com.bosen.marketing.service.IBsPlatformActivityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 平台活动主表(BsPlatformActivity)表控制层
 *
 * @author Lucas
 * @since 2023-09-12 13:52:47
 */
@RestController
@RequestMapping("bsPlatformActivity")
public class BsPlatformActivityController {
    /**
     * 服务对象
     */
    @Resource
    private IBsPlatformActivityService bsPlatformActivityService;

    /**
     * 分页查询所有数据
     * @param pageVO 分页对象
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public ResponseData<PageData<BsPlatformActivity>> pageList(PageVO pageVO) {
        return ResponseData.success();
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail/{id}")
    public ResponseData<BsPlatformActivity> selectOne(@PathVariable("id") String id) {
        return ResponseData.success(this.bsPlatformActivityService.getById(id));
    }

    /**
     * 新增/修改数据
     * @param bsPlatformActivity 实体对象
     * @return 新增结果
     */
    @PostMapping("upsert")
    public ResponseData<Void> upsert(@RequestBody BsPlatformActivity bsPlatformActivity) {
        return ResponseData.success();
    }

    /**
     * 删除数据
     * @param idList 主键结合
     * @return 删除结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody List<String> idList) {
        return ResponseData.judge(this.bsPlatformActivityService.removeByIds(idList));
    }
}

