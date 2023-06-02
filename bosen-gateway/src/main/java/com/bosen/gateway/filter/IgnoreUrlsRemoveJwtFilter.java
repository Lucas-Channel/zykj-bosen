package com.bosen.gateway.filter;

import cn.hutool.json.JSONUtil;
import com.bosen.common.constant.auth.AuthConstant;
import com.bosen.common.constant.common.RedisKeyConstant;
import com.bosen.common.service.RedisService;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;
import java.util.Set;

/**
 * 白名单路径访问时需要移除JWT请求头
 */
@Component
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {
//    @Resource
//    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Resource
    private RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        //白名单路径移除JWT请求头
        Set<Object> objects = redisService.sMembers(RedisKeyConstant.VISIT_URL_WHITE_LIST_KEY);
        List<String> ignoreUrls = JSONUtil.toList(JSONUtil.parseArray(objects), String.class);
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                request = exchange.getRequest().mutate().header(AuthConstant.JWT_TOKEN_HEADER, "").build();
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }
        return chain.filter(exchange);
    }
}
