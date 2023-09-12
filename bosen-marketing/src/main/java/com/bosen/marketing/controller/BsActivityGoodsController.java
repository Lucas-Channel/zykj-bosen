package com.bosen.marketing.controller;


import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.PageVO;
import com.bosen.marketing.domain.BsActivityGoods;
import com.bosen.marketing.service.IBsActivityGoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 活动商品表(BsActivityGoods)表控制层
 *
 * @author Lucas
 * @since 2023-09-12 13:52:27
 */
@RestController
@RequestMapping("bsActivityGoods")
public class BsActivityGoodsController {
    /**
     * 服务对象
     */
    @Resource
    private IBsActivityGoodsService bsActivityGoodsService;

    /**
     * 分页查询所有数据
     * @param pageVO 分页对象
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public ResponseData<PageData<BsActivityGoods>> pageList(PageVO pageVO) {
        return ResponseData.success();
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail/{id}")
    public ResponseData<BsActivityGoods> selectOne(@PathVariable("id") String id) {
        return ResponseData.success(this.bsActivityGoodsService.getById(id));
    }

    /**
     * 新增/修改数据
     * @param bsActivityGoods 实体对象
     * @return 新增结果
     */
    @PostMapping("upsert")
    public ResponseData<Void> upsert(@RequestBody BsActivityGoods bsActivityGoods) {
        return ResponseData.success();
    }

    /**
     * 删除数据
     * @param idList 主键结合
     * @return 删除结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody List<String> idList) {
        return ResponseData.judge(this.bsActivityGoodsService.removeByIds(idList));
    }
}

