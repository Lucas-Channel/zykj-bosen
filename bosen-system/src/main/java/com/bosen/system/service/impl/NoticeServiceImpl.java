package com.bosen.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.system.domain.NoticeDO;
import com.bosen.system.mapper.NoticeMapper;
import com.bosen.system.service.INoticeService;
import org.springframework.stereotype.Service;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/7
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, NoticeDO> implements INoticeService {
}
