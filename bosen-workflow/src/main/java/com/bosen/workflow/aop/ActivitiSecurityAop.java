package com.bosen.workflow.aop;

import com.bosen.common.service.RedisService;
import com.bosen.workflow.vo.SecurityUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;

/**
 * 因使用activiti7最新api需要授权ROLE_ACTIVITI_USER，所以使用切面方式，避免每个接口调用api
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/19
 */
@Aspect
@Component
@Order(1)
public class ActivitiSecurityAop {

    @Resource
    private RedisService redisService;

    @Pointcut("execution(public * com.bosen.workflow.controller.*.*(..))||execution(public * com.bosen.workflow.controller.*.*(..))")
    public void activitiSecurity() {
    }

    @Before("activitiSecurity()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        SecurityUser user = new SecurityUser();
        user.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_ACTIVITI_USER")));
        user.setUsername("admin");
        user.setId("1");
        if (user == null) {
            throw new IllegalStateException("User doesn't exist, please provide a valid user");
        }
        SecurityContextHolder.setContext(new SecurityContextImpl(new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return user.getAuthorities();
            }

            @Override
            public Object getCredentials() {
                return user.getPassword();
            }

            @Override
            public Object getDetails() {
                return user;
            }

            @Override
            public Object getPrincipal() {
                return user;
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return user.getUsername();
            }
        }));
        org.activiti.engine.impl.identity.Authentication.setAuthenticatedUserId(user.getUsername());
    }

}
