package com.bosen.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.system.domain.UnitDO;
import com.bosen.system.mapper.UnitMapper;
import com.bosen.system.service.IUnitService;
import org.springframework.stereotype.Service;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/7
 */
@Service
@Deprecated
public class UnitServiceImpl extends ServiceImpl<UnitMapper, UnitDO> implements IUnitService {
}
