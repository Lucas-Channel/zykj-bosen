package com.bosen.product.controller.merchant;


import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.BsSkuInventoryDO;
import com.bosen.product.service.IBsSkuInventoryService;
import com.bosen.product.vo.request.SkuInventoryQueryVO;
import com.bosen.product.vo.request.SkuInventoryUpsertVO;
import com.bosen.product.vo.response.SkuInventoryDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品SKU库存(BsSkuInventory)表控制层
 *
 * @author Lucas
 * @since 2023-07-14 10:08:18
 */
@RestController
@RequestMapping("/sku/inventory")
public class BsSkuInventoryController {
    /**
     * 服务对象
     */
    @Resource
    private IBsSkuInventoryService bsSkuInventoryService;

    /**
     * 分页查询当前商家的所有库存数据
     *
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public ResponseData<PageData<SkuInventoryDetailVO>> pageList(SkuInventoryQueryVO pageVO) {
        return bsSkuInventoryService.pageList(pageVO);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail/{id}")
    public ResponseData<BsSkuInventoryDO> selectOne(@PathVariable("id") String id) {
        return ResponseData.success(this.bsSkuInventoryService.getById(id));
    }

    /**
     * 修改数据
     * @param bsSkuInventory 实体对象
     * @return 新增结果
     */
    @PostMapping("upsert")
    public ResponseData<Void> upsert(@RequestBody SkuInventoryUpsertVO bsSkuInventory) {
        return bsSkuInventoryService.upsert(bsSkuInventory);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody List<String> idList) {
        return ResponseData.judge(this.bsSkuInventoryService.removeByIds(idList));
    }
}

