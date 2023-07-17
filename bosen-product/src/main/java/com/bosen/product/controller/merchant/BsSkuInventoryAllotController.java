package com.bosen.product.controller.merchant;


import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.vo.request.ApproveInfoVO;
import com.bosen.product.domain.BsSkuInventoryAllotDO;
import com.bosen.product.service.IBsSkuInventoryAllotService;
import com.bosen.product.vo.request.SkuInventoryAllotQueryVO;
import com.bosen.product.vo.request.SkuInventoryAllotUpsertVO;
import com.bosen.product.vo.response.SkuInventoryAllotVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 仓位库存调拨(BsSkuInventoryAllot)表控制层
 *
 * @author Lucas
 * @since 2023-07-14 10:08:18
 */
@RestController
@RequestMapping("/sku/inventory/allot")
public class BsSkuInventoryAllotController {
    /**
     * 服务对象
     */
    @Resource
    private IBsSkuInventoryAllotService bsSkuInventoryAllotService;

    /**
     * 分页查询当前商家所有调拨单数据
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public ResponseData<PageData<SkuInventoryAllotVO>> pageList(SkuInventoryAllotQueryVO queryVO) {
        return bsSkuInventoryAllotService.pageList(queryVO);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail/{id}")
    public ResponseData<BsSkuInventoryAllotDO> selectOne(@PathVariable("id") String id) {
        return ResponseData.success(this.bsSkuInventoryAllotService.getById(id));
    }

    /**
     * 新增/修改数据
     * @param skuInventoryAllotUpsertVO 实体对象
     * @return 新增结果
     */
    @PostMapping("/upsert")
    public ResponseData<Void> upsert(@RequestBody @Valid SkuInventoryAllotUpsertVO skuInventoryAllotUpsertVO) {
        return bsSkuInventoryAllotService.upsert(skuInventoryAllotUpsertVO);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody List<String> idList) {
        return ResponseData.judge(this.bsSkuInventoryAllotService.removeByIds(idList));
    }

    /**
     * 审核
     * @param approveInfoVO 参数
     * @return 结果
     */
    @PostMapping("/approve")
    public ResponseData<Void> approveSkuInventoryAllot(@RequestBody @Valid ApproveInfoVO approveInfoVO) {
        return null;
    }
}

