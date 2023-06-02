package com.bosen.gateway.init;

import cn.hutool.core.collection.CollUtil;
import com.bosen.common.constant.common.RedisKeyConstant;
import com.bosen.common.service.RedisService;
import com.bosen.gateway.domain.GatewayWhiteUrlsDO;
import com.bosen.gateway.service.IGatewayWhiteUrlsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/2
 */
@Component
public class WhiteUrlListInitToRedis {

    @Resource
    private RedisService redisService;

    @Resource
    private IGatewayWhiteUrlsService gatewayWhiteUrlsService;

    @PostConstruct
    public void initWhiteUrlListToRedis() {
        List<GatewayWhiteUrlsDO> list = gatewayWhiteUrlsService.list();
        if (CollUtil.isNotEmpty(list)) {
            redisService.del(RedisKeyConstant.VISIT_URL_WHITE_LIST_KEY);
            List<String> stringList = list.stream().map(GatewayWhiteUrlsDO::getVisitUrl).collect(Collectors.toList());
            redisService.set(RedisKeyConstant.VISIT_URL_WHITE_LIST_KEY, stringList);
        }
    }


}
