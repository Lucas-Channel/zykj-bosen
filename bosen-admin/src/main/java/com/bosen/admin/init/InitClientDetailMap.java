package com.bosen.admin.init;

import cn.hutool.core.collection.CollUtil;
import com.bosen.admin.service.IClientService;
import com.bosen.common.constant.auth.AuthConstant;
import com.bosen.common.domain.api.ClientDetail;
import com.bosen.common.service.RedisService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2024/2/15
 */
@Component
public class InitClientDetailMap {

    @Resource
    private IClientService clientService;

    @Resource
    private RedisService redisService;

    @PostConstruct
    public void initClientDetailMap(){
        List<ClientDetail> list = clientService.lambdaQuery().list();
        if (CollUtil.isNotEmpty(list)) {
            Map<String, String> collect = list.stream().collect(Collectors.toMap(ClientDetail::getClientId, ClientDetail::getClientSecret));
            redisService.del(AuthConstant.CLIENT_KEY);
            redisService.hSetAll(AuthConstant.CLIENT_KEY, collect);
        }
    }
}
