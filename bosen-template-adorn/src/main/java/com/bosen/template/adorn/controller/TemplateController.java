package com.bosen.template.adorn.controller;


import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.template.adorn.service.ITemplateService;
import com.bosen.template.adorn.vo.request.TemplateQueryVO;
import com.bosen.template.adorn.vo.request.TemplateUpsertVO;
import com.bosen.template.adorn.vo.response.TemplateDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 装修模板(TemplateDO)表控制层
 *
 * @author Lucas
 * @since 2023-04-24 18:23:12
 */
@RestController
@RequestMapping("/template")
public class TemplateController {
    /**
     * 服务对象
     */
    @Resource
    private ITemplateService templateService;

    /**
     * 分页查询所有数据
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public ResponseData<PageData<TemplateDetailVO>> pageList(TemplateQueryVO pageVO) {
        return templateService.pageList(pageVO);
    }

    /**
     * 新增/修改数据
     *
     * @param templateUpsertVO 实体对象
     * @return 新增结果
     */
    @PostMapping("upsert")
    public ResponseData<Void> upsert(@RequestBody @Valid TemplateUpsertVO templateUpsertVO) {
        return ResponseData.success();
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestParam("idList") List<String> idList) {
        return ResponseData.judge(this.templateService.removeByIds(idList));
    }
}

