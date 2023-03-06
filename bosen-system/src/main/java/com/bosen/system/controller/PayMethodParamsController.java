package com.bosen.system.controller;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.system.constants.PayMethodParamCodeEnum;
import com.bosen.system.service.IPayMethodParamsService;
import com.bosen.system.vo.request.PayMethodParamsUpsertVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/6
 */
@RestController
@RequestMapping("/payMethodParams")
public class PayMethodParamsController {
    @Resource
    private IPayMethodParamsService payMethodParamsService;

    /**
     * 获取参数key列表
     * @return
     */
    @GetMapping("/listParamsCode")
    public ResponseData<Map<Integer, String>> listParamsCode() {
        return ResponseData.success(Arrays.stream(PayMethodParamCodeEnum.values()).collect(Collectors.toMap(PayMethodParamCodeEnum::getSort, PayMethodParamCodeEnum::getParamCode)));
    }

    /**
     * 新增/修改
     * @param upsertVO 参数
     * @return 结果
     */
    @PostMapping("/upsertParams")
    public ResponseData<Void> upsertParams(@RequestBody List<PayMethodParamsUpsertVO> upsertVO) {
        return payMethodParamsService.upsertParams(upsertVO);
    }

    /**
     * 删除
     * @return 结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody List<Long> ids) {
        return ResponseData.judge(payMethodParamsService.removeBatchByIds(ids));
    }
}
