package com.bosen.auth.config.mobile;

import cn.hutool.core.util.StrUtil;
import com.bosen.common.constant.auth.AuthConstant;
import com.bosen.common.exception.BusinessException;
import lombok.Data;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * TODO 短信验证码验证授权
 *
 * @Author: huilai.huang
 * @Date: 2022/3/3 16:24
 * @Version: 1.0
 */
@Data
public class SmsCodeProduction implements AuthenticationProvider {

    private UserDetailsService userDetailsService;
    private StringRedisTemplate redisTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthToken authenticationToken = (SmsCodeAuthToken) authentication;
        String mobile = (String) authenticationToken.getPrincipal();
        String code = (String) authenticationToken.getCredentials();

        if (code == null) {
            throw new BusinessException("验证码不能为空.");
        }
        if (!code.isEmpty()) {
            String codeKey = AuthConstant.SMS_CODE_PREFIX + mobile;
            String correctCode = redisTemplate.opsForValue().get(codeKey);
            // 验证码比对
            if (StrUtil.isBlank(correctCode) || !code.equals(correctCode)) {
                throw new BusinessException("验证码错误");
            }
            // 比对成功删除缓存的验证码
            redisTemplate.delete(codeKey);
        }
        // todo 等待接入
//        UserDetails userDetails = ((UserServiceImpl) userDetailsService).loadUserByMobile(mobile);
//        SmsCodeAuthToken result = new SmsCodeAuthToken(userDetails, authentication.getCredentials(), new HashSet<>());
//        result.setDetails(authentication.getDetails());
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthToken.class.isAssignableFrom(authentication);
    }
}
