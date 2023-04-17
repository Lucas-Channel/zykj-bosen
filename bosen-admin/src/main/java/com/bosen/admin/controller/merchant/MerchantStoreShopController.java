package com.bosen.admin.controller.merchant;

import com.bosen.admin.service.IStoreShopService;
import com.bosen.admin.vo.response.StoreShopDetailVO;
import com.bosen.admin.vo.resquest.StoreShopApplyVO;
import com.bosen.admin.vo.resquest.StoreShopQueryVO;
import com.bosen.common.constant.response.ResponseData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 店铺开店-控制层-商家后台
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
@RestController
@RequestMapping("/store/shop/merchant")
public class MerchantStoreShopController {

    @Resource
    private IStoreShopService storeShopService;

    /**
     * 店铺上架管理-获取商家店铺所上架商城
     * @param queryVO 参数
     * @return 结果
     */
    @GetMapping("/listStoreShop")
    public ResponseData<List<StoreShopDetailVO>> listStoreShop(StoreShopQueryVO queryVO) {
        return storeShopService.listStoreShopForMerchant(queryVO);
    }

    /**
     * 开店申请
     * @return 结果
     */
    @PostMapping("/openStoreShopApply")
    public ResponseData<Void> openStoreShopApply(@RequestBody @Valid StoreShopApplyVO storeShopApplyVO) {
        return storeShopService.openStoreShopApply(storeShopApplyVO);
    }

    /**
     * 关店申请
     * @return 结果
     */
    @PostMapping("/closeStoreShopApply")
    public ResponseData<Void> closeStoreShopApply(@RequestBody @Valid StoreShopApplyVO storeShopApplyVO) {
        return storeShopService.closeStoreShopApply(storeShopApplyVO);
    }
}
