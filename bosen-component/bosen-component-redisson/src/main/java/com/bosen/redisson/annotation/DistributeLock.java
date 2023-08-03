package com.bosen.redisson.annotation;

import java.lang.annotation.*;

/**
 * key的设置尽量粒度小，提高效率，减少覆盖面
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/3
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributeLock {

    String lockKey() default "";

    long leaseTime() default -1L;

    long waitTime() default -1L;

    boolean showMsg() default true;
}
