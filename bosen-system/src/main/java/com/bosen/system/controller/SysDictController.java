package com.bosen.system.controller;


import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.system.domain.SysDictDO;
import com.bosen.system.service.ISysDictService;
import com.bosen.system.vo.request.DictQueryVO;
import com.bosen.system.vo.request.DictUpsertVO;
import com.bosen.system.vo.response.DictDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 字典管理(BsSysDict)表控制层
 *
 * @author Lucas
 * @since 2023-05-25 16:34:04
 */
@RestController
@RequestMapping("/sys/dict")
public class SysDictController {
    /**
     * 服务对象
     */
    @Resource
    private ISysDictService sysDictService;

    /**
     * 分页查询所有数据
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public ResponseData<PageData<DictDetailVO>> pageList(DictQueryVO pageVO) {
        return sysDictService.pageList(pageVO);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail/{id}")
    public ResponseData<DictDetailVO> selectOne(@PathVariable("id") String id) {
        SysDictDO sysDictDO = this.sysDictService.getById(id);
        DictDetailVO data = new DictDetailVO();
        BeanUtils.copyProperties(sysDictDO, data);
        return ResponseData.success(data);
    }

    /**
     * 新增/修改数据
     * @param dictUpsertVO 实体对象
     * @return 新增结果
     */
    @PostMapping("/upsert")
    public ResponseData<Void> upsert(@RequestBody @Valid DictUpsertVO dictUpsertVO) {
        return sysDictService.upsert(dictUpsertVO);
    }

    /**
     * 删除数据
     * @param idList 主键结合
     * @return 删除结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody List<String> idList) {
        return ResponseData.judge(this.sysDictService.removeByIds(idList));
    }
}

