package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.product.domain.BsSkuInventoryTransactionDO;
import com.bosen.product.mapper.BsSkuInventoryTransactionMapper;
import com.bosen.product.service.IBsSkuInventoryTransactionService;
import org.springframework.stereotype.Service;

/**
 * sku库存事务流水(BsSkuInventoryTransaction)表服务实现类
 *
 * @author Lucas
 * @since 2023-07-17 13:55:11
 */
@Service
public class BsSkuInventoryTransactionServiceImpl extends ServiceImpl<BsSkuInventoryTransactionMapper, BsSkuInventoryTransactionDO> implements IBsSkuInventoryTransactionService {

}

