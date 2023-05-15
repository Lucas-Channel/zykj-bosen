package com.bosen.drools.controller;


import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.drools.domain.DroolsInfoDO;
import com.bosen.drools.service.IDroolsInfoService;
import com.bosen.drools.vo.request.DroolsQueryVO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规则详情表(BsDroolsInfo)表控制层
 *
 * @author Lucas
 * @since 2023-05-12 16:08:41
 */
@RestController
@RequestMapping("/drools/")
public class DroolsInfoController {
    /**
     * 服务对象
     */
    @Resource
    private IDroolsInfoService bsDroolsInfoService;

    /**
     * 分页查询所有数据
     *
     * @param queryVO 查询实体
     * @return 所有数据
     */
    @GetMapping("/pageList")
    @Cacheable(cacheNames = "drools", key="'list:'+#queryVO.current+':'+#queryVO.size+':'+#queryVO.droolsCode+':'+#queryVO.droolsName", unless = "#result?.data == null")
    public ResponseData<PageData<DroolsInfoDO>> pageList(DroolsQueryVO queryVO) {
        return bsDroolsInfoService.pageList(queryVO);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail/{id}")
    public ResponseData<DroolsInfoDO> selectOne(@PathVariable("id") String id) {
        return ResponseData.success(this.bsDroolsInfoService.getById(id));
    }

    /**
     * 新增/修改数据
     *
     * @param bsDroolsInfo 实体对象
     * @return 新增结果
     */
    @PostMapping("upsert")
    public ResponseData<Void> upsert(@RequestBody DroolsInfoDO bsDroolsInfo) {
        return ResponseData.judge(this.bsDroolsInfoService.saveOrUpdate(bsDroolsInfo));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestParam("idList") List<String> idList) {
        return ResponseData.judge(this.bsDroolsInfoService.removeByIds(idList));
    }

    /**
     * 通过决策表生产规则
     * @return 规则代码
     */
    @PostMapping("/importDecisionTables")
    public ResponseData<String> importDecisionTables(@RequestPart("file") MultipartFile file) {
        return bsDroolsInfoService.importDecisionTables(file);
    }
}

