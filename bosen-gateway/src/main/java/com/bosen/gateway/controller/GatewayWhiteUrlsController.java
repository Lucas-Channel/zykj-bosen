package com.bosen.gateway.controller;


import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.PageVO;
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

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param bsGatewayWhiteUrls 查询实体
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
    @GetMapping("/detail/{id}")
    public ResponseData<GatewayWhiteUrlsDO> selectOne(@PathVariable("id") String id) {
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
        return ResponseData.judge(bsGatewayWhiteUrlsService.saveOrUpdate(gatewayWhiteUrlsDO));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestParam("idList") List<String> idList) {
        return ResponseData.judge(this.bsGatewayWhiteUrlsService.removeByIds(idList));
    }
}

