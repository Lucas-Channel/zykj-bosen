package com.bosen.drools.controller;


import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.PageVO;
import com.bosen.drools.domain.DroolsColDO;
import com.bosen.drools.service.IDroolsColService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规则列表(BsDroolsCol)表控制层
 *
 * @author Lucas
 * @since 2023-05-15 14:10:48
 */
@RestController
@RequestMapping("bsDroolsCol")
public class DroolsColController {
    /**
     * 服务对象
     */
    @Resource
    private IDroolsColService bsDroolsColService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param bsDroolsCol 查询实体
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public ResponseData<PageData<DroolsColDO>> pageList(PageVO pageVO) {
        return ResponseData.success();
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail/{id}")
    public ResponseData<DroolsColDO> selectOne(@PathVariable("id") String id) {
        return ResponseData.success(this.bsDroolsColService.getById(id));
    }

    /**
     * 新增/修改数据
     *
     * @param bsDroolsCol 实体对象
     * @return 新增结果
     */
    @PostMapping("upsert")
    public ResponseData<Void> upsert(@RequestBody DroolsColDO bsDroolsCol) {
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
        return ResponseData.judge(this.bsDroolsColService.removeByIds(idList));
    }
}

