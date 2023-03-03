package com.bosen.pay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.pay.domain.AccountTradeRecordsDO;
import com.bosen.pay.mapper.AccountTradeRecordsMapper;
import com.bosen.pay.service.IAccountTradeRecordsService;
import org.springframework.stereotype.Service;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/1
 */
@Service
public class AccountTradeRecordsServiceImpl extends ServiceImpl<AccountTradeRecordsMapper, AccountTradeRecordsDO> implements IAccountTradeRecordsService {
}
