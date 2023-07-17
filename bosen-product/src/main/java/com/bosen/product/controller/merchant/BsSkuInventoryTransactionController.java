package com.bosen.product.controller.merchant;


import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.PageVO;
import com.bosen.product.domain.BsSkuInventoryTransactionDO;
import com.bosen.product.service.IBsSkuInventoryTransactionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * sku库存事务流水(BsSkuInventoryTransaction)表控制层
 *
 * @author Lucas
 * @since 2023-07-17 13:55:11
 */
@RestController
@RequestMapping("sku/inventory/transaction")
public class BsSkuInventoryTransactionController {
    /**
     * 服务对象
     */
    @Resource
    private IBsSkuInventoryTransactionService bsSkuInventoryTransactionService;

    /**
     * 分页查询所有数据
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public ResponseData<PageData<BsSkuInventoryTransactionDO>> pageList(PageVO pageVO) {
        return ResponseData.success();
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail/{id}")
    public ResponseData<BsSkuInventoryTransactionDO> selectOne(@PathVariable("id") String id) {
        return ResponseData.success(this.bsSkuInventoryTransactionService.getById(id));
    }

    /**
     * 新增/修改数据
     * @param bsSkuInventoryTransactionDO 实体对象
     * @return 新增结果
     */
    @PostMapping("upsert")
    public ResponseData<Void> upsert(@RequestBody BsSkuInventoryTransactionDO bsSkuInventoryTransactionDO) {
        return ResponseData.success();
    }

    /**
     * 删除数据
     * @param idList 主键结合
     * @return 删除结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody List<String> idList) {
        return ResponseData.judge(this.bsSkuInventoryTransactionService.removeByIds(idList));
    }
}

