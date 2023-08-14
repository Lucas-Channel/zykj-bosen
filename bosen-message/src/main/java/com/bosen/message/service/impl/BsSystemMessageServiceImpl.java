package com.bosen.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.constant.MessageTargetGroupTypeConstant;
import com.bosen.message.constant.SystemMessageStatusConstant;
import com.bosen.message.domain.BsSystemMessage;
import com.bosen.message.mapper.BsSystemMessageMapper;
import com.bosen.message.service.IBsSystemMessageService;
import com.bosen.message.vo.request.SystemMessageQueryVO;
import com.bosen.message.vo.request.SystemMessageUpsertVO;
import com.bosen.message.vo.response.BsSystemMessageDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 系统消息(BsSystemMessage)表服务实现类
 *
 * @author Lucas
 * @since 2023-08-14 11:52:31
 */
@Service
public class BsSystemMessageServiceImpl extends ServiceImpl<BsSystemMessageMapper, BsSystemMessage> implements IBsSystemMessageService {

    @Override
    public ResponseData<PageData<BsSystemMessageDetailVO>> pageList(SystemMessageQueryVO queryVO) {
        Page<BsSystemMessage> page = this.page(new Page<>(queryVO.getCurrent(), queryVO.getSize()), new LambdaQueryWrapper<BsSystemMessage>()
                .like(StringUtils.hasLength(queryVO.getTitle()), BsSystemMessage::getTitle, queryVO.getTitle())
                .eq(Objects.nonNull(queryVO.getMessageTargetGroupType()), BsSystemMessage::getMessageTargetGroupType, queryVO.getMessageTargetGroupType())
                .eq(Objects.nonNull(queryVO.getStatus()), BsSystemMessage::getStatus, queryVO.getStatus())
                .orderByDesc(BsSystemMessage::getCreateTime));
        return ResponseData.success(new PageData<>(page.getTotal(), page.getRecords().stream().map(i -> {
            BsSystemMessageDetailVO detailVO = new BsSystemMessageDetailVO();
            BeanUtils.copyProperties(i, detailVO);
            detailVO.setMessageTargetGroupTypeName(MessageTargetGroupTypeConstant.getMessageByCode(i.getMessageTargetGroupType()));
            detailVO.setStatusName(SystemMessageStatusConstant.getMessageByCode(i.getStatus()));
            return detailVO;
        }).collect(Collectors.toList())));
    }

    @Override
    public ResponseData<Void> upsert(SystemMessageUpsertVO systemMessageUpsertVO) {
        BsSystemMessage systemMessage;
        if (StringUtils.hasLength(systemMessageUpsertVO.getId())) {
            systemMessage = this.getById(systemMessageUpsertVO.getId());
        } else {
            systemMessage = new BsSystemMessage();
        }
        BeanUtils.copyProperties(systemMessageUpsertVO, systemMessage);
        return ResponseData.judge(this.saveOrUpdate(systemMessage));
    }
}

