package net.zhuruyi.security.broeser;

import net.zhuruyi.security.broeser.authentication.AuthenticationSuccessHandler;
import net.zhuruyi.security.core.Validata.code.ValidateCodeFilter;
import net.zhuruyi.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 23:16 2018/3/12
 * @Modified By:
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler handler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter codeFilter = new ValidateCodeFilter();
        codeFilter.setAuthenticationFailHande(failureHandler);
        http.addFilterBefore(codeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")/*指定登陸界面所在的URL*/
                .loginProcessingUrl("/authentication/form")
                .successHandler(handler)
                .failureHandler(failureHandler)
       /* http.httpBasic()*/
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(), "/code/image").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
