package com.bosen.gateway.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.PageVO;
import com.bosen.gateway.domain.GatewayWhiteUrlsDO;
import com.bosen.gateway.mapper.GatewayWhiteUrlsMapper;
import com.bosen.gateway.service.IGatewayWhiteUrlsService;
import org.springframework.stereotype.Service;

/**
 * 白名单(BsGatewayWhiteUrls)表服务实现类
 *
 * @author Lucas
 * @since 2023-06-02 16:25:00
 */
@Service
public class GatewayWhiteUrlsServiceImpl extends ServiceImpl<GatewayWhiteUrlsMapper, GatewayWhiteUrlsDO> implements IGatewayWhiteUrlsService {

    @Override
    public ResponseData<PageData<GatewayWhiteUrlsDO>> pageList(PageVO pageVO) {
        Page<GatewayWhiteUrlsDO> page = this.page(new Page<>(pageVO.getCurrent(), pageVO.getSize()), new LambdaQueryWrapper<GatewayWhiteUrlsDO>().orderByDesc(GatewayWhiteUrlsDO::getCreateTime));
        return ResponseData.success(new PageData<>(page.getTotal(), page.getRecords()));
    }
}

