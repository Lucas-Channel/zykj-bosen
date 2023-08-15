package com.bosen.drools.init;

import cn.hutool.core.collection.CollUtil;
import com.bosen.common.service.RedisService;
import com.bosen.drools.domain.DroolsScriptDO;
import com.bosen.drools.service.IDroolsScriptService;
import com.bosen.redisson.until.DistributedLockerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 避免redis异常断线，启动服务，判断是否存在未部署的脚本
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/15
 */
@Slf4j
@Component
public class DroolsScriptInit {
    @Resource
    private RedisService redisService;

    @Resource
    private IDroolsScriptService droolsScriptService;

    @Resource
    private DistributedLockerUtil distributedLockerUtil;

    private static final String INIT_SCRIPT_KEY = "init_script_key";

    @PostConstruct
    public void initScript() {
        try {
            if (distributedLockerUtil.tryLock(INIT_SCRIPT_KEY)) {
                List<DroolsScriptDO> list = droolsScriptService.lambdaQuery().list();
                if (CollUtil.isNotEmpty(list)) {
                    List<DroolsScriptDO> scriptDOS = list.stream().filter(i -> !redisService.hasKey(i.getDataSourceId())).collect(Collectors.toList());
                    if (!CollectionUtils.isEmpty(scriptDOS)) {
                        log.info("初始化脚本========》初始化大小：{}", scriptDOS.size());
                        scriptDOS.forEach(i -> {
                            redisService.del(i.getDataSourceId());
                            redisService.set(i.getDataSourceId(), i.getDroolsScript());
                        });
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (distributedLockerUtil.isHeldByCurrentThread(INIT_SCRIPT_KEY)) {
                distributedLockerUtil.unlock(INIT_SCRIPT_KEY);
            }

        }

    }
}
