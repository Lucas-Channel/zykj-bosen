package com.bosen.product.controller.merchant;


import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.PageVO;
import com.bosen.product.domain.BsSkuInventoryAllotDetailDO;
import com.bosen.product.service.IBsSkuInventoryAllotDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 仓位库存调拨明细(BsSkuInventoryAllotDetail)表控制层
 *
 * @author Lucas
 * @since 2023-07-14 10:08:18
 */
@RestController
@RequestMapping("bsSkuInventoryAllotDetail")
public class BsSkuInventoryAllotDetailController {
    /**
     * 服务对象
     */
    @Resource
    private IBsSkuInventoryAllotDetailService bsSkuInventoryAllotDetailService;

    /**
     * 分页查询所有数据
     *
     * @param pageVO 分页对象
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public ResponseData<PageData<BsSkuInventoryAllotDetailDO>> pageList(PageVO pageVO) {
        return ResponseData.success();
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail/{id}")
    public ResponseData<BsSkuInventoryAllotDetailDO> selectOne(@PathVariable("id") String id) {
        return ResponseData.success(this.bsSkuInventoryAllotDetailService.getById(id));
    }

    /**
     * 新增/修改数据
     *
     * @param bsSkuInventoryAllotDetailDO 实体对象
     * @return 新增结果
     */
    @PostMapping("upsert")
    public ResponseData<Void> upsert(@RequestBody BsSkuInventoryAllotDetailDO bsSkuInventoryAllotDetailDO) {
        return ResponseData.success();
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody List<String> idList) {
        return ResponseData.judge(this.bsSkuInventoryAllotDetailService.removeByIds(idList));
    }
}

