package com.bosen.system.controller;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.system.domain.SystemParamsDO;
import com.bosen.system.service.ISystemParamsService;
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
@RequestMapping("/system/params")
public class SystemParamsController {
    @Resource
    private ISystemParamsService systemParamsService;

    /**
     * 列表查询
     * @return 结果
     */
    @GetMapping("/list")
    public ResponseData<List<SystemParamsDO>> list() {
        return ResponseData.success(systemParamsService.list());
    }

    /**
     * 新增/修改
     * @param upsertVO 参数
     * @return 结果
     */
    @PostMapping("/upsertParams")
    public ResponseData<Void> upsertPayMethod(@RequestBody @Valid SystemParamsDO upsertVO) {
        return ResponseData.judge(systemParamsService.saveOrUpdate(upsertVO));
    }

    /**
     * 修改状态
     * @return 结果
     */
    @PostMapping("/updateStatus")
    public ResponseData<Void> updateStatus(@RequestParam Long id, @RequestParam Integer status) {
        return ResponseData.judge(systemParamsService.lambdaUpdate().eq(SystemParamsDO::getId, id).set(SystemParamsDO::getStatus, status).update());
    }

    /**
     * 删除
     * @return 结果
     */
    @PostMapping("/delete")
    public ResponseData<Void> delete(@RequestParam Long id) {
        return ResponseData.judge(systemParamsService.removeById(id));
    }
}
