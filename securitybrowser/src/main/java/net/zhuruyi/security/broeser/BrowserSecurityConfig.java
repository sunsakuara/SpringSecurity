package net.zhuruyi.security.broeser;


import net.zhuruyi.security.broeser.authentication.AuthenticationSuccessHandler;
import net.zhuruyi.security.core.Validata.code.ValidateCodeFilter;
import net.zhuruyi.security.core.properties.SecurityProperties;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

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

    /**
     * 记住我
     */
    @Autowired
    private DataSource dataSource;
    /**
     * 记住我
     *
     * @return
     */
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    /**
     * 记住我
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter codeFilter = new ValidateCodeFilter();
        codeFilter.setAuthenticationFailHande(failureHandler);
        codeFilter.setSecurityProperties(securityProperties);
        codeFilter.afterPropertiesSet();
        http.addFilterBefore(codeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")/*指定登陸界面所在的URL*/
                .loginProcessingUrl("/authentication/form")
                .successHandler(handler)
                .failureHandler(failureHandler)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
       /* http.httpBasic()*/
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(), "/code/*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                /*防护*/
                .csrf().disable();
    }
}
