package com.bosen.admin.controller.merchant;

import com.bosen.admin.service.IStoreService;
import com.bosen.admin.vo.response.StoreDetailVO;
import com.bosen.admin.vo.resquest.StoreUpsertVO;
import com.bosen.common.constant.response.ResponseData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 店铺-控制层-商家后台
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
@RestController
@RequestMapping("/store/merchant")
public class StoreController {

    @Resource
    private IStoreService storeService;

    /**
     * 创建/修改店铺
     * @param storeUpsertVO 参数
     * @return 结果
     */
    @PostMapping("/upsertStore")
    public ResponseData<Void> upsertStore(@RequestBody @Valid StoreUpsertVO storeUpsertVO) {
        return storeService.upsertStore(storeUpsertVO);
    }

    /**
     * 我的店铺-获取商家店铺列表
     * @return 结果
     */
    @GetMapping("/getAllStore")
    public ResponseData<List<StoreDetailVO>> getAllStore() {
        return storeService.getAllStore();
    }



}
