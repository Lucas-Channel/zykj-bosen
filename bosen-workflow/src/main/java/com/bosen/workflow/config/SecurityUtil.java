package com.bosen.workflow.config;

import com.bosen.common.service.RedisService;
import com.bosen.workflow.vo.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;

/**
 * activiti7 官方提供，后续使用aop形式设置
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/18
 */
@Deprecated
//@Component
public class SecurityUtil {
    private Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    @Resource
    private RedisService redisService;

    public void logInAs(String username) {
        SecurityUser user = new SecurityUser();
        user.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_ACTIVITI_USER")));
        user.setUsername("admin");
        user.setId("1");
        if (user == null) {
            throw new IllegalStateException("User " + username + " doesn't exist, please provide a valid user");
        }
        logger.info("> Logged in as: " + username);
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
        org.activiti.engine.impl.identity.Authentication.setAuthenticatedUserId(username);
    }
}
