package com.bosen.drools.service.impl;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.common.service.RedisService;
import com.bosen.drools.engine.api.vo.request.GenRuleScriptReqVO;
import com.bosen.drools.service.IRuleService;
import org.apache.http.util.Asserts;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.StringReader;
import java.util.Map;
import java.util.UUID;

/**
 * 规则引擎基类业务逻辑处理
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/7
 */
@Service
public class RuleServiceImpl implements IRuleService {

    // 全局的容器，存在于内存
    private Map<String, KieContainer> containers;

    @Resource
    private RedisService redisService;

    @Override
    public void checkOrderAndAssignmentCoupon() {

    }

    @Override
    public void checkOrderPromotion() {

    }

    @Override
    public ResponseData<Void> genRuleScript(GenRuleScriptReqVO genRuleScriptReqVO) {
        return null;
    }

    @Override
    public KieContainer buildContainer(String ruleScript) {
        Asserts.notNull(ruleScript, "执行脚本不能为空");
        // 随机生成文件名，临时使用
        String fileName = UUID.randomUUID().toString();
        return buildContainer(ruleScript, fileName);
    }

    private KieContainer buildContainer(String ruleScript, String fileName) {
        // 构建 KieServices
        KieServices kieServices = KieServices.get();
        // drools脚本写入到 kie的文件系统中
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        String path = "/src/main/resources/" + fileName + "_drools.drl";
        kieFileSystem.write(path, kieServices.getResources().newReaderResource(new StringReader(ruleScript)));
        // 读取文件进行构建
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            throw new BusinessException(results.getMessages().toString());
        }
        // 构建 Kie 容器
        return kieServices.newKieContainer(kieBuilder.getKieModule().getReleaseId());
    }

    /**
     * 通过脚本key创建容器
     * @param ruleKey 脚本存在redis中的key
     */
    private void buildContainerByRuleKey(String ruleKey) {
        if (ruleKey == null || containers.containsKey(ruleKey)) {
            return;
        }
        String ruleScript = redisService.get(ruleKey).toString();
        if (StringUtils.hasLength(ruleKey)) {
            containers.put(ruleKey, buildContainer(ruleScript, ruleKey));
        }
    }

    /**
     * 给 kieSession 设置全局变量
     * todo 设置全局变量已经bean
     */
    private void setGlobal(KieSession session) {
        session.setGlobal("bean对象", "");
    }



}
