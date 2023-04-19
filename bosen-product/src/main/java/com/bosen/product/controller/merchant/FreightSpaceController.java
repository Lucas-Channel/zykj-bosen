package com.bosen.product.controller.merchant;

import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.service.IFreightSpaceService;
import com.bosen.product.vo.request.FreightSpaceQueryVO;
import com.bosen.product.vo.request.FreightSpaceUpdateStatusVO;
import com.bosen.product.vo.request.FreightSpaceUpsertVO;
import com.bosen.product.vo.response.FreightSpaceDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 商家-仓位管理
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/19
 */
@RestController
@RequestMapping("/freight/space")
public class FreightSpaceController {

    @Resource
    private IFreightSpaceService freightSpaceService;

    /**
     * 分页查询商家仓位
     * @param queryVO
     * @return
     */
    @GetMapping("/pageList")
    public ResponseData<PageData<FreightSpaceDetailVO>> pageList(FreightSpaceQueryVO queryVO) {
        return freightSpaceService.pageList(queryVO);
    }

    /**
     * 查询商家所有仓位
     * @return 结果
     */
    @GetMapping("/listAll")
    public ResponseData<List<FreightSpaceDetailVO>> listAll() {
        return freightSpaceService.listAll();
    }

    /**
     * 新增/修改仓位库存
     * @return
     */
    @PostMapping("/upsertFreightSpace")
    public ResponseData<Void> upsertFreightSpace(@RequestBody @Valid FreightSpaceUpsertVO upsertVO) {
        return freightSpaceService.upsertFreightSpace(upsertVO);
    }

    /**
     * 更改状态
     * @return
     */
    @PostMapping("/updateStatus")
    public ResponseData<Void> updateStatus(@RequestBody @Valid FreightSpaceUpdateStatusVO updateStatusVO) {
        return freightSpaceService.updateStatus(updateStatusVO);
    }

    /**
     * 删除
     * @return
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody List<String> ids) {
        return ResponseData.judge(freightSpaceService.removeBatchByIds(ids));
    }


}
