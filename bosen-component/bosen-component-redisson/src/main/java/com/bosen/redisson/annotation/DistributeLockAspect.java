package com.bosen.redisson.annotation;

import com.bosen.redisson.until.DistributedLockerUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
        long leaseTime = distributeLock.leaseTime();
        long waitTime = distributeLock.waitTime();
        // 加锁的key
        String lockKey = distributeLock.lockKey();

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
            if (distributeLock.showMsg()) {
                throw new Exception("您的请求正在处理中，请稍后再试。");
            } else {
                return null;
            }
        }
    }

}
