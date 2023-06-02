package com.bosen.system.init;

import com.bosen.common.constant.common.YesOrNoConstant;
import com.bosen.common.constant.pay.PayMethodEnum;
import com.bosen.redisson.until.DistributedLockerUtil;
import com.bosen.system.constants.FundModelEnums;
import com.bosen.system.domain.PayMethodDO;
import com.bosen.system.service.IPayMethodService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * system 服务初始化数据
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/1
 */
@Component
public class InitSystemConfigData {

    @Resource
    private IPayMethodService payMethodService;

    @Resource
    private DistributedLockerUtil distributedLockerUtil;

    private static final String INIT_PAY_METHOD_KEY = "init_pay_method_key";

    @Async("defaultPoolExecutor")
    @PostConstruct
    public void initData() {
        initPayMethod();
    }

    private void initPayMethod() {
        long count = payMethodService.count();
        // 避免集群下多服务
        if (!Objects.equals(PayMethodEnum.values().length, count) && distributedLockerUtil.tryLock(INIT_PAY_METHOD_KEY)) {
            Map<String, String> map = payMethodService.list().stream().collect(Collectors.toMap(PayMethodDO::getPayMethodCode, PayMethodDO::getPayMethodName, (v1, v2) -> v1));
            // 初始化数据
            List<PayMethodDO> methodDOS = Arrays.stream(PayMethodEnum.values()).filter(p -> !map.containsKey(p.getCode())).map(i -> {
                PayMethodDO payMethodDO = new PayMethodDO();
                payMethodDO.setPayMethodCode(i.getCode());
                payMethodDO.setPayMethodName(i.getMessage());
                payMethodDO.setEnableFlag(YesOrNoConstant.YES);
                payMethodDO.setFundModel(FundModelEnums.PLATFORM_EXCHANGE.getCode());
                return payMethodDO;
            }).collect(Collectors.toList());
            payMethodService.saveBatch(methodDOS);
        }
    }
}
