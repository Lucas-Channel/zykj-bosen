package com.bosen.gateway.controller;


import com.bosen.common.constant.common.RedisKeyConstant;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.PageVO;
import com.bosen.common.service.RedisService;
import com.bosen.gateway.domain.GatewayWhiteUrlsDO;
import com.bosen.gateway.service.IGatewayWhiteUrlsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 白名单(BsGatewayWhiteUrls)表控制层
 *
 * @author Lucas
 * @since 2023-06-02 16:25:00
 */
@RestController
@RequestMapping("/gateway/white/url")
public class GatewayWhiteUrlsController {
    /**
     * 服务对象
     */
    @Resource
    private IGatewayWhiteUrlsService bsGatewayWhiteUrlsService;

    @Resource
    private RedisService redisService;

    /**
     * 分页查询所有数据
     *
     * @param pageVO 分页对象
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public ResponseData<PageData<GatewayWhiteUrlsDO>> pageList(PageVO pageVO) {
        return bsGatewayWhiteUrlsService.pageList(pageVO);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail")
    public ResponseData<GatewayWhiteUrlsDO> selectOne(String id) {
        return ResponseData.success(this.bsGatewayWhiteUrlsService.getById(id));
    }

    /**
     * 新增/修改数据
     *
     * @param gatewayWhiteUrlsDO 实体对象
     * @return 新增结果
     */
    @PostMapping("upsert")
    public ResponseData<Void> upsert(@RequestBody GatewayWhiteUrlsDO gatewayWhiteUrlsDO) {
        redisService.sAdd(RedisKeyConstant.VISIT_URL_WHITE_LIST_KEY, gatewayWhiteUrlsDO.getVisitUrl());
        return ResponseData.judge(bsGatewayWhiteUrlsService.saveOrUpdate(gatewayWhiteUrlsDO));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody List<String> idList) {
        return ResponseData.judge(this.bsGatewayWhiteUrlsService.removeByIds(idList));
    }
}

