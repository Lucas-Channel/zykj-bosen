package com.bosen.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.constant.BusinessTypeConstant;
import com.bosen.message.constant.UserMessageStatusConstant;
import com.bosen.message.domain.BsUserMessage;
import com.bosen.message.mapper.BsUserMessageMapper;
import com.bosen.message.service.IBsUserMessageService;
import com.bosen.message.vo.request.UserMessageQueryVO;
import com.bosen.message.vo.request.UserMessageUpsertVO;
import com.bosen.message.vo.response.BsUserMessageDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户消息(BsUserMessage)表服务实现类
 *
 * @author Lucas
 * @since 2023-08-14 15:26:30
 */
@Service
public class BsUserMessageServiceImpl extends ServiceImpl<BsUserMessageMapper, BsUserMessage> implements IBsUserMessageService {

    @Override
    public ResponseData<PageData<BsUserMessageDetailVO>> pageList(UserMessageQueryVO queryVO) {
        Page<BsUserMessage> page = this.page(new Page<>(queryVO.getCurrent(), queryVO.getSize()), new LambdaQueryWrapper<BsUserMessage>()
                .like(StringUtils.hasLength(queryVO.getTitle()), BsUserMessage::getTitle, queryVO.getTitle())
                .eq(Objects.nonNull(queryVO.getBusinessType()), BsUserMessage::getBusinessType, queryVO.getBusinessType())
                .eq(Objects.nonNull(queryVO.getType()), BsUserMessage::getType, queryVO.getType())
                .eq(Objects.nonNull(queryVO.getStatus()), BsUserMessage::getStatus, queryVO.getStatus())
                .orderByDesc(BsUserMessage::getCreateTime));
        return ResponseData.success(new PageData<>(page.getTotal(), page.getRecords().stream().map(i -> {
            BsUserMessageDetailVO detailVO = new BsUserMessageDetailVO();
            BeanUtils.copyProperties(i, detailVO);
            detailVO.setBusinessTypeName(BusinessTypeConstant.getMessageByCode(i.getBusinessType()));
            detailVO.setStatusName(UserMessageStatusConstant.getMessageByCode(i.getStatus()));
            return detailVO;
        }).collect(Collectors.toList())));
    }

    @Override
    public ResponseData<Void> upsert(UserMessageUpsertVO messageUpsertVO) {
        BsUserMessage bsUserMessage;
        if (StringUtils.hasLength(messageUpsertVO.getId())) {
            bsUserMessage = this.getById(messageUpsertVO.getId());
        } else {
            bsUserMessage = new BsUserMessage();
        }
        BeanUtils.copyProperties(messageUpsertVO, bsUserMessage);
        return ResponseData.judge(this.saveOrUpdate(bsUserMessage));
    }
}

