package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.product.domain.ProductApproveRecordDO;
import com.bosen.product.mapper.ProductApproveRecordMapper;
import com.bosen.product.service.IProductApproveRecordService;
import org.springframework.stereotype.Service;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/12
 */
@Service
public class ProductApproveRecordServiceImpl extends ServiceImpl<ProductApproveRecordMapper, ProductApproveRecordDO> implements IProductApproveRecordService {
}
