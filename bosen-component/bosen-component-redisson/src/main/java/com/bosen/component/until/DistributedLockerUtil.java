package com.bosen.component.until;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁，注意：lock拿不到锁会一直等待。tryLock是去尝试，拿不到就返回false，拿到返回true。
 * 一旦设置了锁的释放时间，看门狗机制将不复存在
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/28
 */
@Slf4j
@Component
public class DistributedLockerUtil {

    @Resource
    private RedissonClient redissonClient;

    public RLock getLock(String lockKey) {
        return redissonClient.getLock(lockKey);
    }
    /**
     * 获取锁，如果锁不可用，则当前线程处于休眠状态，直到获得锁为止。
     *
     * @param lockKey 锁key值
     */
   public void lock(String lockKey) {
        this.getLock(lockKey).lock();
   }

    /**
     * 带超时时间的锁
     *
     * @param lockKey   key
     * @param leaseTime 上锁后自动释放锁时间
     * @param unit      时间单位
     */
    public void lock(String lockKey, long leaseTime, TimeUnit unit) {
        this.getLock(lockKey).lock(leaseTime, unit);
    }

    /**
     * 释放锁
     *
     * @param lockKey 锁key值
     */
    public void unlock(String lockKey) {
        this.getLock(lockKey).unlock();
    }

    /**
     * 尝试获取锁,获取到立即返回true,未获取到立即返回false。
     *
     * @param lockKey 锁key值
     * @return 是否获取到锁
     */
    public boolean tryLock(String lockKey) {
        return this.getLock(lockKey).tryLock();
    }

    /**
     * 尝试获取锁,在等待时间内获取到锁则返回true,否则返回false,如果获取到锁,则要么执行完后程序释放锁,
     * 要么在给定的超时时间leaseTime后释放锁。
     *
     * @param lockKey   锁key值
     * @param waitTime  等待时间
     * @param leaseTime 释放时间
     * @param unit      时间单位
     */
    public boolean tryLock(String lockKey, long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException{
        return this.getLock(lockKey).tryLock(waitTime, leaseTime, unit);
    }

    /**
     * 锁是否被任意一个线程锁持有
     *
     * @param lockKey 锁key值
     */
    public boolean isLocked(String lockKey) {
        return this.getLock(lockKey).isLocked();
    }

    /**
     * 查询当前线程是否保持此锁定
     * @param lockKey 锁key值
     * @return 结果
     */
    public boolean isHeldByCurrentThread(String lockKey) {
        return this.getLock(lockKey).isHeldByCurrentThread();
    }
}
