package com.bosen.drools.controller;


import com.bosen.common.constant.response.ResponseData;
import com.bosen.drools.domain.DroolsActionColItemDO;
import com.bosen.drools.service.IDroolsActionColItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规则结果列明细控制层
 *
 * @author Lucas
 */
@RestController
@RequestMapping("/drools/result/col/item")
public class DroolsActionColItemController {
    /**
     * 服务对象
     */
    @Resource
    private IDroolsActionColItemService droolsActionColItemService;

    /**
     * 通过列id查询明细
     * @param colId 列id
     * @return list
     */
    @GetMapping("/listByColId/{colId}")
    public ResponseData<List<DroolsActionColItemDO>> listByColId(@PathVariable("colId") String colId) {
        return ResponseData.success(droolsActionColItemService.lambdaQuery().eq(DroolsActionColItemDO::getDroolsActionColId, colId).orderByDesc(DroolsActionColItemDO::getCreateTime).list());
    }
}

