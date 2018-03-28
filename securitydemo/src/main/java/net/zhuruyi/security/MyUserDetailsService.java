package net.zhuruyi.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 20:44 2018/3/13
 * @Modified By:
 */
@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 表單登陸
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*logger.info("登录用户名：" + s);
        //根據用戶名查找用戶信息
        //根据查找到的用户信息判断是否被冻结

        return new User(s, passwordEncoder.encode("123456"),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));*/
        return getSocialUserDetails(username);
    }

    /**
     * 第三方登陸
     */
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        return getSocialUserDetails(userId);
    }

    private SocialUserDetails getSocialUserDetails(String userId) {
        logger.info("登陸用戶名：" + userId);
        //根據用戶名查找用戶信息
        //根據查找到的用戶信息判斷用戶是否被凍結

        String password = passwordEncoder.encode("123456");
        logger.info("數據庫密碼是：" + password);
        return new SocialUser(userId, password,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}







