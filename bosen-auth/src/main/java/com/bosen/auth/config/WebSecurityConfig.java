package com.bosen.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * SpringSecurity配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * gateway 资源管理器有注入
     */
//    @Bean
//    @ConditionalOnBean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.anonymous().disable().authorizeRequests()
//                .antMatchers("/rsa/publicKey").permitAll()
//                .antMatchers("/oauth/**").permitAll()
//                .anyRequest().authenticated()
//                .and().csrf().disable();
//        return http.build();
//    }

    @Resource
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
