package com.bosen.admin.controller.admin;

import com.bosen.admin.service.IStoreShopService;
import com.bosen.admin.vo.response.StoreShopDetailVO;
import com.bosen.admin.vo.resquest.StoreShopQueryVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.vo.request.ApproveBatchInfoVO;
import com.bosen.common.vo.request.ApproveInfoVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 店铺开店-控制层-平台后台
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
@RestController
@RequestMapping("/store/shop/admin")
public class AdminStoreShopController {

    @Resource
    private IStoreShopService storeShopService;

    /**
     * 查询所有上架的店铺-平台后台，可依据状态切换数据
     * @param queryVO 参数
     * @return 结果
     */
    @GetMapping("/pageList")
    public ResponseData<PageData<StoreShopDetailVO>> pageList(StoreShopQueryVO queryVO) {
        return storeShopService.pageList(queryVO);
    }

    /**
     * 审核开店申请
     * @return 结果
     */
    @PostMapping("/approveOpenStoreShopApply")
    public ResponseData<Void> approveOpenStoreApply(@RequestBody @Valid ApproveInfoVO approveInfoVO) {
        return storeShopService.approveOpenStoreShopApply(approveInfoVO);
    }

    /**
     * 审核开店申请-批量
     * @return 结果
     */
    @PostMapping("/approveOpenStoreShopApplyBatch")
    public ResponseData<Void> approveOpenStoreShopApplyBatch(@RequestBody @Valid ApproveBatchInfoVO approveBatchInfoVO) {
        return storeShopService.approveOpenStoreShopApplyBatch(approveBatchInfoVO);
    }

    /**
     * 审核关店申请
     * @return 结果
     */
    @PostMapping("/approveCloseStoreShopApply")
    public ResponseData<Void> approveCloseStoreShopApply(@RequestBody @Valid ApproveInfoVO approveInfoVO) {
        return storeShopService.approveCloseStoreShopApply(approveInfoVO);
    }

    /**
     * 审核关店申请-批量
     * @return 结果
     */
    @PostMapping("/approveCloseStoreShopApplyBatch")
    public ResponseData<Void> approveCloseStoreShopApplyBatch(@RequestBody @Valid ApproveBatchInfoVO approveBatchInfoVO) {
        return storeShopService.approveCloseStoreShopApplyBatch(approveBatchInfoVO);
    }
}
