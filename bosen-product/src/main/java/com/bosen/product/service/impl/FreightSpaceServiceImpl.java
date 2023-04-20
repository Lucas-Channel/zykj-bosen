package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.product.domain.FreightSpaceDO;
import com.bosen.product.mapper.FreightSpaceMapper;
import com.bosen.product.service.IFreightSpaceService;
import com.bosen.product.vo.request.FreightSpaceQueryVO;
import com.bosen.product.vo.request.FreightSpaceUpdateStatusVO;
import com.bosen.product.vo.request.FreightSpaceUpsertVO;
import com.bosen.product.vo.response.FreightSpaceDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 仓位逻辑业务层
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/19
 */
@Slf4j
@Service
public class FreightSpaceServiceImpl extends ServiceImpl<FreightSpaceMapper, FreightSpaceDO> implements IFreightSpaceService {
    @Override
    public ResponseData<PageData<FreightSpaceDetailVO>> pageList(FreightSpaceQueryVO queryVO) {
        String merchantId = "";
        String merchantRoleId = "";
        Page<FreightSpaceDO> page = this.page(new Page<>(queryVO.getCurrent(), queryVO.getSize()), new LambdaQueryWrapper<FreightSpaceDO>()
                .like(StringUtils.hasLength(queryVO.getName()), FreightSpaceDO::getName, queryVO.getName())
                .eq(StringUtils.hasLength(queryVO.getCode()), FreightSpaceDO::getCode, queryVO.getCode())
                .eq(Objects.nonNull(queryVO.getStatus()), FreightSpaceDO::getStatus, queryVO.getStatus())
                .eq(FreightSpaceDO::getMerchantId, merchantId)
                .eq(FreightSpaceDO::getMerchantRoleId, merchantRoleId)
                .orderByDesc(FreightSpaceDO::getCreateTime)
        );
        return ResponseData.success(new PageData<>(page.getTotal(), page.getRecords().stream().map(i -> {
            FreightSpaceDetailVO detailVO = new FreightSpaceDetailVO();
            BeanUtils.copyProperties(i, detailVO);
            return detailVO;
        }).collect(Collectors.toList())));
    }

    @Override
    public ResponseData<List<FreightSpaceDetailVO>> listAll() {
        String merchantId = "";
        String merchantRoleId = "";
        return ResponseData.success(this.lambdaQuery()
                .eq(FreightSpaceDO::getMerchantId, merchantId)
                .eq(FreightSpaceDO::getMerchantRoleId, merchantRoleId)
                .orderByDesc(FreightSpaceDO::getCreateTime).list()
                .stream().map(i -> {
                    FreightSpaceDetailVO detailVO = new FreightSpaceDetailVO();
                    BeanUtils.copyProperties(i, detailVO);
                    return detailVO;
                }).collect(Collectors.toList()));
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> upsertFreightSpace(FreightSpaceUpsertVO upsertVO) {
        String merchantId = "";
        String merchantRoleId = "";
        String merchantName = "";
        FreightSpaceDO freightSpaceDO = new FreightSpaceDO();
        BeanUtils.copyProperties(upsertVO, freightSpaceDO);
        freightSpaceDO.setMerchantId(merchantId).setMerchantRoleId(merchantRoleId).setMerchantName(merchantName);
        if (!StringUtils.hasLength(freightSpaceDO.getId())) {
            freightSpaceDO.setStatus(1);
        }
        return ResponseData.judge(this.saveOrUpdate(freightSpaceDO));
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> updateStatus(FreightSpaceUpdateStatusVO updateStatusVO) {
        FreightSpaceDO spaceDO = this.lambdaQuery().eq(FreightSpaceDO::getId, updateStatusVO.getId()).one();
        if (Objects.isNull(spaceDO)) {
            throw new BusinessException(ResponseCode.FREIGHT_SPACE_NOT_EXIT_ERROR);
        }
        spaceDO.setStatus(updateStatusVO.getStatus());
        return ResponseData.judge(this.updateById(spaceDO));
    }
}
