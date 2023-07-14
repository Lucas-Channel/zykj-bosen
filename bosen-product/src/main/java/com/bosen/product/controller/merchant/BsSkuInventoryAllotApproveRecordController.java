package com.bosen.product.controller.merchant;


import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.BsSkuInventoryAllotApproveRecordDO;
import com.bosen.product.service.IBsSkuInventoryAllotApproveRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 调拨单审核记录(BsSkuInventoryAllotApproveRecord)表控制层
 *
 * @author Lucas
 * @since 2023-07-14 16:03:57
 */
@RestController
@RequestMapping("/sku/inventory/allot/approve")
public class BsSkuInventoryAllotApproveRecordController {
    /**
     * 服务对象
     */
    @Resource
    private IBsSkuInventoryAllotApproveRecordService bsSkuInventoryAllotApproveRecordService;

    /**
     * 分页查询所有数据
     * @param allotId 调拨单id
     * @return 所有数据
     */
    @GetMapping("/list")
    public ResponseData<List<BsSkuInventoryAllotApproveRecordDO>> list(String allotId) {
        return ResponseData.success(bsSkuInventoryAllotApproveRecordService.lambdaQuery().eq(BsSkuInventoryAllotApproveRecordDO::getAllotId, allotId).list());
    }

}

