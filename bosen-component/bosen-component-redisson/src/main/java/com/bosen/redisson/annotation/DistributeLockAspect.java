package com.bosen.redisson.annotation;

import com.bosen.redisson.until.DistributedLockerUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 此处order优先级设置为1，优先进入，最后离开，保证事务问题
 *
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/3
 */
@Order(1)
@Aspect
@Component
@Slf4j
public class DistributeLockAspect {

    @Resource
    private DistributedLockerUtil distributedLockerUtil;

    @Pointcut("@annotation(distributeLock)")
    public void pointCut(DistributeLock distributeLock) {
    }

    @Around(value = "pointCut(distributeLock)", argNames = "pjp,distributeLock")
    public Object around(ProceedingJoinPoint pjp, DistributeLock distributeLock) throws Throwable {
        // 释放锁时间，如果该值不是-1，那么锁到期即释放，看门狗机制不生效
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext(pjp.getArgs());
        setStandardEvaluationContextVariables(standardEvaluationContext, pjp);
        String lockKey = getValue(distributeLock.lockKey(), standardEvaluationContext, String.class);
        long waitTime = getValue(distributeLock.waitTime(), standardEvaluationContext, long.class);
        long leaseTime = getValue(distributeLock.leaseTime(), standardEvaluationContext, long.class);
        boolean showMsg = getValue(distributeLock.showMsg(), standardEvaluationContext, boolean.class);

        if (distributedLockerUtil.tryLock(lockKey, waitTime, leaseTime, TimeUnit.SECONDS)) {
            try {
                log.info("lock success, key = [{}]", lockKey);
                return pjp.proceed();
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            } finally {
                if (distributedLockerUtil.isHeldByCurrentThread(lockKey)) {
                    distributedLockerUtil.unlock(lockKey);
                    log.info("releaseLock success, key = [{}]", lockKey);
                }
            }
        } else {
            if (showMsg) {
                throw new Exception("您的请求正在处理中，请稍后再试。");
            } else {
                return null;
            }
        }
    }

    /**
     * 设置上下文数据
     */
    private void setStandardEvaluationContextVariables(StandardEvaluationContext standardEvaluationContext, ProceedingJoinPoint pjp) {
        // 解析spel数据
        Object[] args = pjp.getArgs();
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        // 获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer localVariableTable = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = localVariableTable.getParameterNames(method);
        if (Objects.isNull(args) || args.length <= 0) {
            return;
        }
        // 把方法参数放入SPEL上下文中
        for (int i = 0; i < Objects.requireNonNull(paraNameArr).length; i++) {
            standardEvaluationContext.setVariable(paraNameArr[i], args[i]);
        }
    }

    private <T> T getValue(Object key, StandardEvaluationContext standardEvaluationContext, Class<T> cls) {
        if(!key.toString().matches("^#.*.$")) {
            return (T) key;
        }
        ExpressionParser parser = new SpelExpressionParser();
        return parser.parseExpression(key.toString()).getValue(standardEvaluationContext, cls);
    }
}
