package com.bosen.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.PageVO;
import com.bosen.gateway.domain.GatewayWhiteUrlsDO;

/**
 * 白名单(BsGatewayWhiteUrls)表服务接口
 *
 * @author Lucas
 * @since 2023-06-02 16:25:00
 */
public interface IGatewayWhiteUrlsService extends IService<GatewayWhiteUrlsDO> {
    ResponseData<PageData<GatewayWhiteUrlsDO>> pageList(PageVO pageVO);
}

