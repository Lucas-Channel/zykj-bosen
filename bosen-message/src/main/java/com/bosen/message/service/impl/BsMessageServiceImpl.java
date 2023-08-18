package com.bosen.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.message.api.vo.request.MessageUpsertVO;
import com.bosen.message.constant.BusinessTypeConstant;
import com.bosen.message.constant.MessageTargetGroupTypeConstant;
import com.bosen.message.constant.MessageType;
import com.bosen.message.domain.BsMessage;
import com.bosen.message.mapper.BsMessageMapper;
import com.bosen.message.service.IBsMessageService;
import com.bosen.message.vo.request.MessageQueryVO;
import com.bosen.message.vo.response.BsMessageDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 消息(BsMessage)表服务实现类
 *
 * @author Lucas
 * @since 2023-08-18 11:14:56
 */
@Service
public class BsMessageServiceImpl extends ServiceImpl<BsMessageMapper, BsMessage> implements IBsMessageService {

    @Override
    public ResponseData<Void> upsert(MessageUpsertVO messageUpsertVO) {
        BsMessage message = new BsMessage();
        BeanUtils.copyProperties(messageUpsertVO, message);
        boolean save = this.save(message);
        if (save) {
            // 发送ws消息
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    // todo 待定
                }
            });
        }
        return ResponseData.judge(save);
    }

    @Override
    public ResponseData<BsMessageDetailVO> getDetail(String id) {
        BsMessage bsMessage = this.lambdaQuery().eq(BsMessage::getId, id).oneOpt().orElseThrow(() -> new BusinessException(ResponseCode.DATA_NOT_EXIT_ERROR));
        BsMessageDetailVO detailVO = new BsMessageDetailVO();
        BeanUtils.copyProperties(bsMessage, detailVO);
        detailVO.setBusinessTypeName(BusinessTypeConstant.getMessageByCode(bsMessage.getBusinessType()));
        detailVO.setMessageTargetGroupTypeName(MessageTargetGroupTypeConstant.getMessageByCode(bsMessage.getMessageTargetGroupType()));
        return ResponseData.success(detailVO);
    }

    @Override
    public ResponseData<PageData<BsMessageDetailVO>> pageUserMessageList(MessageQueryVO pageVO) {
        // todo 替换登录信息
        Page<BsMessageDetailVO> page = new Page<>(pageVO.getCurrent(), pageVO.getSize());
        pageVO.setReceiveUserId("1");
        pageVO.setReceiveUserRoleId("1");
        IPage<BsMessageDetailVO> pageList = baseMapper.pageList(page, pageVO);
        return ResponseData.success(new PageData<>(pageList.getTotal(), pageList.getRecords()));
    }

    @Override
    public ResponseData<PageData<BsMessageDetailVO>> pageSystemMessageList(MessageQueryVO queryVO) {
        Page<BsMessage> page = this.page(new Page<>(queryVO.getCurrent(), queryVO.getSize()), new LambdaQueryWrapper<BsMessage>()
                .like(StringUtils.hasLength(queryVO.getTitle()), BsMessage::getTitle, queryVO.getTitle())
                .eq(Objects.nonNull(queryVO.getMessageTargetGroupType()), BsMessage::getMessageTargetGroupType, queryVO.getMessageTargetGroupType())
                .eq(BsMessage::getBusinessType, BusinessTypeConstant.SYSTEM.getCode())
                .eq(BsMessage::getType, MessageType.SYSTEM.getCode())
                .orderByDesc(BsMessage::getCreateTime));
        return ResponseData.success(new PageData<>(page.getTotal(), page.getRecords().stream().map(i -> {
            BsMessageDetailVO detailVO = new BsMessageDetailVO();
            BeanUtils.copyProperties(i, detailVO);
            detailVO.setMessageTargetGroupTypeName(MessageTargetGroupTypeConstant.getMessageByCode(i.getMessageTargetGroupType()));
            detailVO.setBusinessTypeName(BusinessTypeConstant.getMessageByCode(i.getBusinessType()));
            return detailVO;
        }).collect(Collectors.toList())));
    }

    @Override
    public ResponseData<Integer> getUnreadCount(String userId, String roleId) {
        return null;
    }
}

