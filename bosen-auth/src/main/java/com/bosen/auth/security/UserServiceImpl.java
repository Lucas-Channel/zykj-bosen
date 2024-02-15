package com.bosen.auth.security;

import com.bosen.admin.api.feign.AdminFeignService;
import com.bosen.auth.constant.MessageConstant;
import com.bosen.auth.domain.SecurityUser;
import com.bosen.auth.feign.PortalMemberFeignService;
import com.bosen.common.domain.UserDto;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 用户管理业务类
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Resource
    private HttpServletRequest request;
    @Resource
    private AdminFeignService adminFeignService;

    @Resource
    private PortalMemberFeignService memberFeignService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
        /*if (AuthConstant.ADMIN_CLIENT_ID.equals(clientId)) {
            userDto = adminUserFeignService.loadUserByUsername(username);
        } else if (AuthConstant.MERCHANT_CLIENT_ID.equals(clientId)) {
            userDto = adminUserFeignService.loginMerchantByMobile(username);
        } else {
            userDto = memberFeignService.loadUserByUsername(username);
        }
        if (userDto==null) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }*/
        UserDto userDto = adminFeignService.loadUserByUsername(username);
        if (Objects.isNull(userDto)) {
            throw new DisabledException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        userDto.setClientId(clientId);
        SecurityUser securityUser = new SecurityUser(userDto);
        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }

}
