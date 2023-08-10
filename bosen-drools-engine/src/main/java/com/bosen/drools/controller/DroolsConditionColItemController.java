package com.bosen.drools.controller;


import com.bosen.common.constant.response.ResponseData;
import com.bosen.drools.domain.DroolsConditionColItemDO;
import com.bosen.drools.service.IDroolsConditionColItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规则列明细表控制层
 * @author Lucas
 */
@RestController
@RequestMapping("/drools/condition/col/item")
public class DroolsConditionColItemController {
    /**
     * 服务对象
     */
    @Resource
    private IDroolsConditionColItemService droolsConditionColItemService;

    /**
     * 通过列id查询明细
     * @param colId 列id
     * @return list
     */
    @GetMapping("/listByColId/{colId}")
    public ResponseData<List<DroolsConditionColItemDO>> listByColId(@PathVariable("colId") String colId) {
        return ResponseData.success(droolsConditionColItemService.lambdaQuery().eq(DroolsConditionColItemDO::getDroolsConditionColId, colId).orderByDesc(DroolsConditionColItemDO::getCreateTime).list());
    }

}

