package com.bosen.drools.controller;


import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.drools.domain.DroolsScriptDO;
import com.bosen.drools.engine.api.vo.request.GenRuleScriptReqVO;
import com.bosen.drools.service.IDroolsScriptService;
import com.bosen.drools.service.IRuleService;
import com.bosen.drools.vo.request.DroolsQueryVO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 规则详情表(BsDroolsInfo)表控制层
 *
 * @author Lucas
 * @since 2023-05-12 16:08:41
 */
@RestController
@RequestMapping("/drools/script/")
public class DroolsScriptController {
    /**
     * 服务对象
     */
    @Resource
    private IDroolsScriptService bsDroolsInfoService;

    @Resource
    private IRuleService ruleService;

    /**
     * 分页查询所有数据
     *
     * @param queryVO 查询实体
     * @return 所有数据
     */
    @GetMapping("/pageList")
    @Cacheable(cacheNames = "drools", key="'list:'+#queryVO.current+':'+#queryVO.size+':'+#queryVO.droolsCode+':'+#queryVO.droolsName", unless = "#result?.data == null")
    public ResponseData<PageData<DroolsScriptDO>> pageList(DroolsQueryVO queryVO) {
        return bsDroolsInfoService.pageList(queryVO);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail/{id}")
    public ResponseData<DroolsScriptDO> selectOne(@PathVariable("id") String id) {
        return ResponseData.success(this.bsDroolsInfoService.getById(id));
    }

    /**
     * 新增/修改数据
     *
     * @param bsDroolsInfo 实体对象
     * @return 新增结果
     */
    @PostMapping("upsert")
    public ResponseData<Void> upsert(@RequestBody DroolsScriptDO bsDroolsInfo) {
        return ResponseData.judge(this.bsDroolsInfoService.saveOrUpdate(bsDroolsInfo));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody List<String> idList) {
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

    @PostMapping("/genRuleScript")
    public ResponseData<Void> genRuleScript(@RequestBody @Valid GenRuleScriptReqVO genRuleScriptReqVO) {
        return ruleService.genRuleScript(genRuleScriptReqVO);
    }

    @GetMapping("/testScript")
    public ResponseData<Void> testScript() {
        return ruleService.checkOrderAndAssignmentCoupon();
    }
}

