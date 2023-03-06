package com.bosen.system.controller;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.system.service.IPayMethodService;
import com.bosen.system.vo.request.PayMethodUpsertVO;
import com.bosen.system.vo.response.PayMethodVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/6
 */
@RestController
@RequestMapping("/payMethod")
public class PayMethodController {
    @Resource
    private IPayMethodService payMethodService;

    /**
     * 列表查询
     * @return 结果
     */
    @GetMapping("/list")
    public ResponseData<List<PayMethodVO>> list() {
        return ResponseData.success(payMethodService.list().stream().map(i -> {
            PayMethodVO de = new PayMethodVO();
            BeanUtils.copyProperties(i, de);
            return de;
        }).collect(Collectors.toList()));
    }

    /**
     * 新增/修改支付方式
     * @param upsertVO 参数
     * @return 结果
     */
    @PostMapping("/upsertPayMethod")
    public ResponseData<Void> upsertPayMethod(@RequestBody @Valid PayMethodUpsertVO upsertVO) {
        return payMethodService.upsertPayMethod(upsertVO);
    }

    /**
     * 修改状态
     * @return 结果
     */
    @PostMapping("/updateStatus")
    public ResponseData<Void> updateStatus(@RequestParam Long id, @RequestParam Integer status) {
        return payMethodService.updateStatus(id, status);
    }

    /**
     * 删除
     * @return 结果
     */
    @PostMapping("/delete")
    public ResponseData<Void> delete(@RequestParam Long id) {
        return ResponseData.judge(payMethodService.removeById(id));
    }
}
