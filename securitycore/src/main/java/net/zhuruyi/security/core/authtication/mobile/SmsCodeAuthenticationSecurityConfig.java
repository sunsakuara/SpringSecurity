package net.zhuruyi.security.core.authtication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 23:03 2018/3/19
 * @Modified By:
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends
        SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        //1.配置过滤器
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        //2.调取manager
        smsCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(
                AuthenticationManager.class));
        //3。设置成功处理和失败处理
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        //4.配置provider
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

        //5.加入springsecurity安全框架中
        builder.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);
    }
}
