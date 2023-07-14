package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.common.util.OrderNoStringUtil;
import com.bosen.product.constant.SkuInventoryAllotStatusEnum;
import com.bosen.product.domain.BsSkuInventoryAllotApproveRecordDO;
import com.bosen.product.domain.BsSkuInventoryAllotDO;
import com.bosen.product.mapper.BsSkuInventoryAllotMapper;
import com.bosen.product.service.IBsSkuInventoryAllotApproveRecordService;
import com.bosen.product.service.IBsSkuInventoryAllotDetailService;
import com.bosen.product.service.IBsSkuInventoryAllotService;
import com.bosen.product.vo.request.SkuInventoryAllotDetailVO;
import com.bosen.product.vo.request.SkuInventoryAllotQueryVO;
import com.bosen.product.vo.request.SkuInventoryAllotUpsertVO;
import com.bosen.product.vo.response.SkuInventoryAllotVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 仓位库存调拨(BsSkuInventoryAllot)表服务实现类
 *
 * @author Lucas
 * @since 2023-07-14 10:08:18
 */
@Service
public class BsSkuInventoryAllotServiceImpl extends ServiceImpl<BsSkuInventoryAllotMapper, BsSkuInventoryAllotDO> implements IBsSkuInventoryAllotService {

    @Resource
    private IBsSkuInventoryAllotDetailService bsSkuInventoryAllotDetailService;

    @Resource
    private IBsSkuInventoryAllotApproveRecordService allotApproveRecordService;

    @Override
    public ResponseData<PageData<SkuInventoryAllotVO>> pageList(SkuInventoryAllotQueryVO queryVO) {
        Page<BsSkuInventoryAllotDO> page = this.page(new Page<>(queryVO.getCurrent(), queryVO.getSize()), new LambdaQueryWrapper<BsSkuInventoryAllotDO>()
                .like(StringUtils.hasLength(queryVO.getAllotNo()), BsSkuInventoryAllotDO::getAllotNo, queryVO.getAllotNo())
                .eq(Objects.nonNull(queryVO.getAllotType()), BsSkuInventoryAllotDO::getAllotType, queryVO.getAllotType())
                .eq(Objects.nonNull(queryVO.getStatus()), BsSkuInventoryAllotDO::getStatus, queryVO.getStatus())
                .eq(BsSkuInventoryAllotDO::getMerchantId, null)
                .eq(BsSkuInventoryAllotDO::getMerchantRoleId, null)
                .orderByDesc(BsSkuInventoryAllotDO::getCreateTime)
        );
        return ResponseData.success(new PageData<>(page.getTotal(), page.getRecords().stream().map(this::turnObj).collect(Collectors.toList())));
    }


    @Override
    @Transactional
    public ResponseData<Void> upsert(SkuInventoryAllotUpsertVO skuInventoryAllotUpsertVO) {
        BsSkuInventoryAllotDO allotDO;
        if (StringUtils.hasLength(skuInventoryAllotUpsertVO.getId())) {
            if (!Objects.equals(skuInventoryAllotUpsertVO.getStatus(), SkuInventoryAllotStatusEnum.WAIT_CONFIRM.getCode())) {
                throw new BusinessException(ResponseCode.ALLOT_CANNOT_EDIT_ERROR);
            }
            allotDO = this.getById(skuInventoryAllotUpsertVO.getId());
            if (Objects.isNull(allotDO)) {
                throw new BusinessException(ResponseCode.ALLOT_NOT_EXIT_ERROR);
            }
        } else {
            allotDO = new BsSkuInventoryAllotDO();
            allotDO.setAllotNo(OrderNoStringUtil.randomUniqueNumberWithDate("DB", 14));
            allotDO.setAllotTime(LocalDateTime.now());
            allotDO.setAllotAmount(Objects.isNull(skuInventoryAllotUpsertVO.getAllotAmount()) ? skuInventoryAllotUpsertVO.getDetail().stream().map(SkuInventoryAllotDetailVO::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP) : skuInventoryAllotUpsertVO.getAllotAmount());
            allotDO.setAllotAmount(Objects.isNull(skuInventoryAllotUpsertVO.getAllotQuantity()) ? skuInventoryAllotUpsertVO.getDetail().stream().map(SkuInventoryAllotDetailVO::getQuantity).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP) : skuInventoryAllotUpsertVO.getAllotQuantity());
        }
        BeanUtils.copyProperties(skuInventoryAllotUpsertVO, allotDO);
        allotDO.setStatus(skuInventoryAllotUpsertVO.getSaveAndConfirm() ? SkuInventoryAllotStatusEnum.WAIT_APPROVE.getCode() : SkuInventoryAllotStatusEnum.WAIT_CONFIRM.getCode());
        // 保存调拨单主数据
        this.saveOrUpdate(allotDO);
        // 保存调拨单明细数据
        skuInventoryAllotUpsertVO.getDetail().forEach(i -> i.setAllotId(allotDO.getId()));
        bsSkuInventoryAllotDetailService.addAllotDetail(skuInventoryAllotUpsertVO.getDetail(), allotDO.getId());
        // 如果一键提交，需要生成审核记录
        if (skuInventoryAllotUpsertVO.getSaveAndConfirm()) {
            BsSkuInventoryAllotApproveRecordDO allotApproveRecordDO = new BsSkuInventoryAllotApproveRecordDO();
            allotApproveRecordDO.setAllotId(allotDO.getId());
            allotApproveRecordDO.setAgreeAdvice("一键提交");
            allotApproveRecordDO.setStatus(allotDO.getStatus());
            allotApproveRecordDO.setOperationUserId(null);
            allotApproveRecordDO.setOperationUserName(null);
            allotApproveRecordDO.setOperationUserRoleId(null);
            allotApproveRecordService.save(allotApproveRecordDO);
        }
        return ResponseData.success();
    }

    private SkuInventoryAllotVO turnObj(BsSkuInventoryAllotDO data) {
        SkuInventoryAllotVO vo = new SkuInventoryAllotVO();
        BeanUtils.copyProperties(data, vo);
        return vo;
    }
}

