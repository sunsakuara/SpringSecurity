package net.zhuruyi.security.broeser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 20:44 2018/3/13
 * @Modified By:
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("登录用户名：" + s);
        //根據用戶名查找用戶信息
        //根据查找到的用户信息判断是否被冻结

        return new User(s, passwordEncoder.encode("123456"),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
