package com.bosen.common.service;

import org.springframework.aop.framework.AopContext;

/**
 * 获取代理对象 self.
 * 用于解决嵌套事务下，自己调用自己方法直接使用self.method();使用方法，接口层继承即可；ProxySelf<IAService>
 *
 */
public interface ProxySelf<T> {
    /**
     * 取得当前对象的代理.
     * 
     * @return 代理对象,如果未被代理,则抛出 IllegalStateException
     */
    @SuppressWarnings("unchecked")
    default T self() {
        return (T) AopContext.currentProxy();
    }
}
