package com.bosen.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.PageVO;
import com.bosen.system.domain.UnitDO;
import com.bosen.system.service.IUnitService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/7
 */
@RestController
@RequestMapping("/unit")
public class UnitController {
    @Resource
    private IUnitService unitService;

    /**
     * 列表查询
     * @return 结果
     */
    @GetMapping("/list")
    public ResponseData<List<UnitDO>> list() {
        return ResponseData.success(unitService.list());
    }

    /**
     * 商品分页列表
     * @return 结果
     */
    @GetMapping("/pages")
    public ResponseData<PageData<UnitDO>> listPages(PageVO queryVO) {
        Page<UnitDO> list = unitService.page(new Page<>(queryVO.getCurrent(), queryVO.getSize()),
                new LambdaQueryWrapper<UnitDO>().orderByDesc(UnitDO::getCreateTime));
        return ResponseData.success(new PageData<>(list.getTotal(), list.getRecords()));
    }

    /**
     * 新增/修改
     * @param upsertVO 参数
     * @return 结果
     */
    @PostMapping("/upsertUnit")
    public ResponseData<Void> upsertPayMethod(@RequestBody @Valid UnitDO upsertVO) {
        return ResponseData.judge(unitService.saveOrUpdate(upsertVO));
    }

    /**
     * 修改状态
     * @return 结果
     */
    @PostMapping("/updateStatus")
    public ResponseData<Void> updateStatus(@RequestParam Long id, @RequestParam Integer status) {
        return ResponseData.judge(unitService.lambdaUpdate().eq(UnitDO::getId, id).set(UnitDO::getStatus, status).update());
    }

    /**
     * 删除
     * @return 结果
     */
    @PostMapping("/delete")
    public ResponseData<Void> delete(@RequestParam Long id) {
        return ResponseData.judge(unitService.removeById(id));
    }
}
