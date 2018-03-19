package net.zhuruyi.security.core.authtication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 22:35 2018/3/19
 * @Modified By:
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        SmsCodeAuthenticationToken token = (SmsCodeAuthenticationToken) authentication;
        UserDetails user = userDetailsService.loadUserByUsername((String) token.getPrincipal());
        if (null == user) {
            throw new InternalAuthenticationServiceException("无法获得用户信息");
        }
        //生成验证成功的Token，传入相应的用户信息
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user,
                user.getAuthorities());
        authenticationResult.setDetails(token.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(
            UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}