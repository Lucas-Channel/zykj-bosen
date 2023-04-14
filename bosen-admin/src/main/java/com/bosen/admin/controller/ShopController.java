package com.bosen.admin.controller;

import com.bosen.admin.domain.ShopDO;
import com.bosen.admin.service.IShopService;
import com.bosen.admin.vo.response.ShopDetailVO;
import com.bosen.admin.vo.resquest.ShopUpsertVO;
import com.bosen.common.constant.response.ResponseData;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商城-控制层
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private IShopService shopService;

    /**
     * 查询所有的商城
     * @return 结果
     */
    @GetMapping("/all")
    public ResponseData<List<ShopDetailVO>> shopALl() {
       return ResponseData.success(shopService.list().stream().map(i -> {
            ShopDetailVO detailVO = new ShopDetailVO();
            BeanUtils.copyProperties(i,detailVO);
            return detailVO;
        }).collect(Collectors.toList()));
    }

    /**
     * 新增修改商城
     * @param shopUpsertVO 参数
     * @return 结果
     */
    @PostMapping("/upsertShop")
    public ResponseData<Void> upsertShop(@RequestBody @Valid ShopUpsertVO shopUpsertVO) {
        ShopDO shopDO = new ShopDO();
        BeanUtils.copyProperties(shopUpsertVO, shopDO);
        return ResponseData.judge(shopService.saveOrUpdate(shopDO));
    }


}
