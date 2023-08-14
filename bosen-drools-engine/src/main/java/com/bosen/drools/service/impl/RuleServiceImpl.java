package com.bosen.drools.service.impl;

import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.common.service.RedisService;
import com.bosen.common.util.JacksonUtils;
import com.bosen.drools.constant.DroolsConstant;
import com.bosen.drools.domain.DroolsScriptDO;
import com.bosen.drools.engine.api.vo.request.GenActionParamVO;
import com.bosen.drools.engine.api.vo.request.GenConditionParamVO;
import com.bosen.drools.engine.api.vo.request.GenRuleScriptReqVO;
import com.bosen.drools.service.IDroolsScriptService;
import com.bosen.drools.service.IRuleService;
import com.bosen.drools.vo.TestPerson;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.Asserts;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 规则引擎基类业务逻辑处理
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/7
 */
@Slf4j
@Service
public class RuleServiceImpl implements IRuleService {

    @Resource
    private IDroolsScriptService droolsScriptService;

    private Map<String, KieContainer> containers;

    @Resource
    private RedisService redisService;

    @SneakyThrows
    @Override
    public ResponseData<Void> checkOrderAndAssignmentCoupon() {
        // 设置drools日期格式
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
        buildContainerByRuleKey("12ewqe2eqweq2");
        KieSession kieSession = containers.get("12ewqe2eqweq2").newKieSession();
//            setGlobal(kieSession);
        // 设置全局变量
        TestPerson testPerson = new TestPerson();
        testPerson.setAge(12);
        kieSession.insert(testPerson);
        kieSession.fireAllRules();
        kieSession.dispose();
        log.info("执行规则后结果：【{}】", JacksonUtils.toJson(testPerson, new ObjectMapper()));
        return ResponseData.success();
    }

    @Override
    public ResponseData<Void> checkOrderPromotion() {
        return null;
    }

    @Override
    @Transactional
    public ResponseData<Void> genRuleScript(GenRuleScriptReqVO genRuleScriptReqVO) {
        if (Objects.nonNull(genRuleScriptReqVO.getEffectiveEndDate()) && LocalDateTime.parse(genRuleScriptReqVO.getEffectiveEndDate()).compareTo(LocalDateTime.now()) < 0) {
            throw new BusinessException(ResponseCode.END_DATE_LESS_THAN_NOW_ERROR);
        }
        DroolsScriptDO scriptDO = droolsScriptService.lambdaQuery().eq(DroolsScriptDO::getDataSourceId, genRuleScriptReqVO.getDataSourceId())
                .eq(DroolsScriptDO::getDroolsType, genRuleScriptReqVO.getDroolsType())
                .one();
        if (Objects.nonNull(scriptDO)) {
            // 删除数据库中的脚本数据
            boolean remove = droolsScriptService.lambdaUpdate().eq(DroolsScriptDO::getDataSourceId, genRuleScriptReqVO.getDataSourceId())
                    .eq(DroolsScriptDO::getDroolsType, genRuleScriptReqVO.getDroolsType())
                    .remove();
            if (!remove) {
                throw new BusinessException(ResponseCode.DELETE_SCRIPT_ERROR);
            }
            // 删除redis中存留的脚本数据
            redisService.del(genRuleScriptReqVO.getDataSourceId());
        }
        redisService.del(genRuleScriptReqVO.getDataSourceId());
        // 对数据分组
        Map<String, List<GenConditionParamVO>> conditionGroup = genRuleScriptReqVO.getConditionParams().stream().collect(Collectors.groupingBy(GenConditionParamVO::getGroupName));
        Map<String, List<GenActionParamVO>> actionGroup = genRuleScriptReqVO.getActionParams().stream().collect(Collectors.groupingBy(GenActionParamVO::getGroupName));
        // 对于分组来说activation_group的值必须一致，这样针对同一个组内的规则，名称使用DataSourceId，只有一个才能被触发，groupName用于表明ruleName，
        StringBuilder droolsScript = new StringBuilder();
        List<String> pathList = genRuleScriptReqVO.getConditionParams().stream().filter(Objects::nonNull).map(GenConditionParamVO::getDroolsConditionColObjectNamePath).distinct().collect(Collectors.toList());
        pathList.forEach(i -> droolsScript.append("import ").append(i).append(";\n"));
        int priorityAll = conditionGroup.keySet().size();
        conditionGroup.forEach((k, v) -> {
            StringBuilder ruleScriptStr = new StringBuilder("rule \"" + DroolsConstant.rule_prefix);
            StringBuilder ruleCondition = genRuleCondition(v);
            StringBuilder ruleAction = genRuleAction(actionGroup.get(k));
            ruleScriptStr.append(k).append("_").append(genRuleScriptReqVO.getDataSourceId()).append("\"\t")
                    .append(" \n\tno-loop true \n\tlock-on-active true \n\tactivation-group \"")
                    .append(genRuleScriptReqVO.getDataSourceId())
                    .append(" \n\tsalience ")
                    .append(priorityAll - v.get(0).getPriority())
                    .append("\"\nwhen \n\t")
                    .append(ruleCondition).append("\nthen \n\t").append(ruleAction).append("\nend ");
            droolsScript.append(ruleScriptStr).append("\n");
        });
        DroolsScriptDO droolsScriptDO = new DroolsScriptDO();
        droolsScriptDO.setDroolsScript(droolsScript.toString());
        droolsScriptDO.setDataSourceId(genRuleScriptReqVO.getDataSourceId());
        droolsScriptDO.setDroolsType(genRuleScriptReqVO.getDroolsType());
        droolsScriptDO.setDroolsCode(genRuleScriptReqVO.getCouponCode());
        droolsScriptDO.setDroolsName(genRuleScriptReqVO.getDroolScriptName());
        boolean save = droolsScriptService.save(droolsScriptDO);
        if (!save) {
            throw new BusinessException(ResponseCode.SAVE_SCRIPT_ERROR);
        }
        redisService.set(genRuleScriptReqVO.getDataSourceId(), droolsScript.toString());
        return ResponseData.success();
    }

    private StringBuilder genRuleCondition(List<GenConditionParamVO> conditionParamVOS) {
        StringBuilder conStr = new StringBuilder();
        Map<String, List<GenConditionParamVO>> collect = conditionParamVOS.stream().collect(Collectors.groupingBy(GenConditionParamVO::getDroolsConditionColId));
        collect.forEach((k, v) -> {
            // 遍历每个条件模板下的明细
            StringBuilder conditionScript = new StringBuilder();
            conditionScript.append("$")
                    .append(v.get(0).getDroolsConditionColObjectName())
                    .append(":")
                    .append(v.get(0).getDroolsConditionColObjectName())
                    .append("(");
            String template = v.get(0).getDroolsConditionTemplate();
            for (GenConditionParamVO j : v) {
                // 替换属性key ， val
                template = template.replace(j.getDroolsConditionColItemColNameKey(), j.getDroolsConditionColItemColName());
                // 替换操作符
                template = template.replace(j.getDroolsConditionColItemOperatorKey(), j.getDroolsConditionColItemOperator());
                // 替换值val
                template = template.replace(j.getConditionValKey(), j.getConditionVal());
                conditionScript.append(template);
                template = v.get(0).getDroolsConditionTemplate();
                conditionScript.append(" && ");
            }
            conditionScript.delete(conditionScript.lastIndexOf("&&"), conditionScript.length());
            conditionScript.append(")");
            conStr.append(conditionScript).append(" &&");
        });
        // 删除最后一个 &&符号
        conStr.delete(conStr.lastIndexOf("&&"), conStr.length());
        return conStr;
    }

    private StringBuilder genRuleAction(List<GenActionParamVO> actionParamVOS) {
        StringBuilder conStr = new StringBuilder();
        Map<String, List<GenActionParamVO>> collect = actionParamVOS.stream().collect(Collectors.groupingBy(GenActionParamVO::getDroolsActionColId));
        collect.forEach((k, v) -> {
            // 遍历每个结果模板下的明细
            StringBuilder ruleScript = new StringBuilder();
            ruleScript.append("$")
                    .append(v.get(0).getDroolsActionColObjectName())
                    .append(".");
            String template = v.get(0).getDroolsActionTemplate();
            for (GenActionParamVO j : v) {
                // 替换属性key ， val
                template = template.replace(j.getDroolsActionColItemColNameKey(), j.getDroolsActionColItemColName());
                // 替换值val
                template = template.replace(j.getActionValKey(), j.getActionVal());
                ruleScript.append(template).append(";");
                template = v.get(0).getDroolsActionTemplate();
            }
            conStr.append(ruleScript).append("\n");
        });
        return conStr;
    }

    private KieContainer buildContainerScript(String ruleScript, String fileName) {
        Asserts.notNull(ruleScript, "执行脚本不能为空");
        KieServices kieServices = KieServices.get();

        // 将 drools 脚本写入 Kie 的文件系统
        KieFileSystem fileSystem = kieServices.newKieFileSystem();
        String path = "/src/main/resources/dynamic_"+ fileName +"_Rule.drl";
        fileSystem.write(path, kieServices.getResources().newReaderResource(new StringReader(ruleScript)));

        // 读取 Kie 文件系统中的文件进行构建
        KieBuilder kieBuilder = kieServices.newKieBuilder(fileSystem).buildAll();
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
        String ruleScript = redisService.get(ruleKey).toString();
        if (containers == null) {
            containers = new HashMap<>();
        }
        if (StringUtils.hasLength(ruleKey)) {
            containers.put(ruleKey, buildContainerScript(ruleScript, ruleKey));
//            buildContainerScript(ruleScript, ruleKey);
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
