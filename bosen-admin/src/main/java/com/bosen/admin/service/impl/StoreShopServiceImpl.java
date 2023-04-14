package com.bosen.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.admin.constant.StoreOpenCloseStatusEnum;
import com.bosen.admin.domain.StoreShopDO;
import com.bosen.admin.mapper.StoreShopMapper;
import com.bosen.admin.service.IStoreShopService;
import com.bosen.admin.vo.response.StoreShopDetailVO;
import com.bosen.admin.vo.resquest.StoreShopApplyVO;
import com.bosen.admin.vo.resquest.StoreShopQueryVO;
import com.bosen.common.constant.common.YesOrNoConstant;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.common.vo.request.ApproveBatchInfoVO;
import com.bosen.common.vo.request.ApproveInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 商家店铺-商城
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
@Service
public class StoreShopServiceImpl extends ServiceImpl<StoreShopMapper, StoreShopDO> implements IStoreShopService {

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> openStoreShopApply(StoreShopApplyVO storeShopApplyVO) {
        StoreShopDO storeShopDO = this.lambdaQuery().eq(StoreShopDO::getShopId, storeShopApplyVO.getShopId()).eq(StoreShopDO::getStoreId, storeShopApplyVO.getStoreId()).one();
        if (Objects.nonNull(storeShopDO)) {
            throw new BusinessException(ResponseCode.STORE_HAS_SHOP_ERROR);
        }
        storeShopDO = new StoreShopDO();
        BeanUtils.copyProperties(storeShopApplyVO, storeShopDO);
        storeShopDO.setStatus(StoreOpenCloseStatusEnum.WAIT_APPROVE_OPEN_STORE_APPLY.getCode());
        return ResponseData.judge(this.save(storeShopDO));
    }

    @Override
    public ResponseData<Void> closeStoreShopApply(StoreShopApplyVO storeShopApplyVO) {
        StoreShopDO storeShop = this.lambdaQuery().eq(StoreShopDO::getShopId, storeShopApplyVO.getShopId()).eq(StoreShopDO::getStoreId, storeShopApplyVO.getStoreId()).one();
        if (Objects.isNull(storeShop)) {
            throw new BusinessException(ResponseCode.STORE_NOT_EXIT_ERROR);
        }
        if (!Objects.equals(storeShop.getStatus(), StoreOpenCloseStatusEnum.OPEN_STORE_APPLY_APPROVE_PASS.getCode())) {
            throw new BusinessException(ResponseCode.STORE_SHOP_CANNOT_CLOSE_ERROR);
        }
        storeShop.setStatus(StoreOpenCloseStatusEnum.WAIT_APPROVE_CLOSE_STORE_APPLY.getCode());
        return ResponseData.judge(this.updateById(storeShop));
    }

    @Override
    public ResponseData<List<StoreShopDetailVO>> listStoreShopForMerchant(StoreShopQueryVO queryVO) {
        String merchantId = null;
        String merchantRoleId = null;
        return ResponseData.success(baseMapper.listStoreShopForMerchant(queryVO, merchantId, merchantRoleId));
    }

    @Override
    public ResponseData<PageData<StoreShopDetailVO>> pageList(StoreShopQueryVO queryVO) {
        Page<StoreShopDetailVO> page = new Page<>(queryVO.getCurrent(), queryVO.getSize());
        IPage<StoreShopDetailVO> pageList = baseMapper.pageList(page, queryVO);
        return ResponseData.success(new PageData<>(pageList.getTotal(), pageList.getRecords()));
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> approveOpenStoreShopApply(ApproveInfoVO approveInfoVO) {
        StoreShopDO storeShop = baseMapper.selectById(approveInfoVO.getOriginalId());
        if (Objects.isNull(storeShop)) {
            throw new BusinessException(ResponseCode.STORE_NOT_EXIT_ERROR);
        }
        if (!Objects.equals(storeShop.getStatus(), StoreOpenCloseStatusEnum.WAIT_APPROVE_OPEN_STORE_APPLY.getCode())) {
            throw new BusinessException(ResponseCode.WAIT_APPROVE_OPEN_STORE_APPLY_ERROR);
        }
        storeShop.setStatus(Objects.equals(approveInfoVO.getAgree(), YesOrNoConstant.YES) ? StoreOpenCloseStatusEnum.OPEN_STORE_APPLY_APPROVE_PASS.getCode() : StoreOpenCloseStatusEnum.OPEN_STORE_APPLY_APPROVE_REFUSE.getCode());
        return ResponseData.judge(this.updateById(storeShop));
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> approveOpenStoreShopApplyBatch(ApproveBatchInfoVO approveBatchInfoVO) {
        List<StoreShopDO> list = baseMapper.selectBatchIds(approveBatchInfoVO.getOriginalId());
        if (Objects.equals(list.size(), approveBatchInfoVO.getOriginalId().size())) {
            throw new BusinessException(ResponseCode.STORE_NOT_EXIT_ERROR);
        }
        List<StoreShopDO> notSit = list.stream().filter(i -> !Objects.equals(i.getStatus(), StoreOpenCloseStatusEnum.WAIT_APPROVE_OPEN_STORE_APPLY.getCode())).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(notSit)) {
            throw new BusinessException(ResponseCode.WAIT_APPROVE_OPEN_STORE_APPLY_ERROR);
        }
        list.forEach(i -> i.setStatus(Objects.equals(approveBatchInfoVO.getAgree(), YesOrNoConstant.YES) ? StoreOpenCloseStatusEnum.OPEN_STORE_APPLY_APPROVE_PASS.getCode() : StoreOpenCloseStatusEnum.OPEN_STORE_APPLY_APPROVE_REFUSE.getCode()));
        return ResponseData.judge(this.updateBatchById(list));
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> approveCloseStoreShopApplyBatch(ApproveBatchInfoVO approveBatchInfoVO) {
        List<StoreShopDO> listClose = baseMapper.selectBatchIds(approveBatchInfoVO.getOriginalId());
        if (Objects.equals(listClose.size(), approveBatchInfoVO.getOriginalId().size())) {
            throw new BusinessException(ResponseCode.STORE_NOT_EXIT_ERROR);
        }
        List<StoreShopDO> notSit = listClose.stream().filter(i -> !Objects.equals(i.getStatus(), StoreOpenCloseStatusEnum.WAIT_APPROVE_CLOSE_STORE_APPLY.getCode())).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(notSit)) {
            throw new BusinessException(ResponseCode.WAIT_APPROVE_CLOSE_STORE_APPLY_ERROR);
        }
        listClose.forEach(i -> i.setStatus(Objects.equals(approveBatchInfoVO.getAgree(), YesOrNoConstant.YES) ? StoreOpenCloseStatusEnum.CLOSE_STORE_APPLY_APPROVE_PASS.getCode() : StoreOpenCloseStatusEnum.CLOSE_STORE_APPLY_APPROVE_REFUSE.getCode()));
        return ResponseData.judge(this.updateBatchById(listClose));
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> approveCloseStoreShopApply(ApproveInfoVO approveInfoVO) {
        StoreShopDO storeShop = baseMapper.selectById(approveInfoVO.getOriginalId());
        if (Objects.isNull(storeShop)) {
            throw new BusinessException(ResponseCode.STORE_NOT_EXIT_ERROR);
        }
        if (!Objects.equals(storeShop.getStatus(), StoreOpenCloseStatusEnum.WAIT_APPROVE_CLOSE_STORE_APPLY.getCode())) {
            throw new BusinessException(ResponseCode.WAIT_APPROVE_CLOSE_STORE_APPLY_ERROR);
        }
        storeShop.setStatus(Objects.equals(approveInfoVO.getAgree(), YesOrNoConstant.YES) ? StoreOpenCloseStatusEnum.CLOSE_STORE_APPLY_APPROVE_PASS.getCode() : StoreOpenCloseStatusEnum.CLOSE_STORE_APPLY_APPROVE_REFUSE.getCode());
        return ResponseData.judge(this.updateById(storeShop));
    }

}
