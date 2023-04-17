package com.bosen.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.admin.constant.StoreStatusEnum;
import com.bosen.admin.domain.StoreDO;
import com.bosen.admin.mapper.StoreMapper;
import com.bosen.admin.service.IStoreService;
import com.bosen.admin.vo.response.StoreDetailVO;
import com.bosen.admin.vo.resquest.StoreQueryVO;
import com.bosen.admin.vo.resquest.StoreUpsertVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 商家店铺
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, StoreDO> implements IStoreService {
    @Override
    public ResponseData<PageData<StoreDetailVO>> pageList(StoreQueryVO queryVO) {
        Page<StoreDO> page = this.page(new Page<>(queryVO.getCurrent(), queryVO.getSize()), new LambdaQueryWrapper<StoreDO>()
                .eq(Objects.nonNull(queryVO.getStatus()), StoreDO::getStatus, queryVO.getStatus())
                .like(StringUtils.hasLength(queryVO.getName()), StoreDO::getName, queryVO.getName())
                .orderByDesc(StoreDO::getCreateTime));
        return ResponseData.success(new PageData<>(page.getTotal(), page.getRecords().stream().map(i -> {
            StoreDetailVO detailVO = new StoreDetailVO();
            BeanUtils.copyProperties(i, detailVO);
            return detailVO;
        }).collect(Collectors.toList())));
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> upsertStore(StoreUpsertVO storeUpsertVO) {
        StoreDO storeDO = new StoreDO();
        BeanUtils.copyProperties(storeUpsertVO, storeDO);
        String merchantId = null, merchantRoleId = null;
        storeDO.setMerchantId(merchantId).setMerchantRoleId(merchantRoleId).setStatus(StoreStatusEnum.NORMAL.getCode());
        return ResponseData.judge(this.saveOrUpdate(storeDO));
    }

    @Override
    public ResponseData<List<StoreDetailVO>> getAllStore() {
        List<StoreDO> list = this.lambdaQuery().eq(StoreDO::getMerchantId, null).eq(StoreDO::getMerchantRoleId, null).list();
        return ResponseData.success(list.stream().map(i -> {
            StoreDetailVO detailVO = new StoreDetailVO();
            BeanUtils.copyProperties(i, detailVO);
            return detailVO;
        }).collect(Collectors.toList()));
    }
}
