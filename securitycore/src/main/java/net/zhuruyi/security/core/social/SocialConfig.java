package net.zhuruyi.security.core.social;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 23:54 2018/3/22
 * @Modified By:
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    /**
     * 数据源
     */
    @Autowired
    private DataSource dataSource;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(
            ConnectionFactoryLocator connectionFactoryLocator) {
        /*reposiroty.setTablePrefix("imooc_")*/
        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator,
                Encryptors.noOpText());
    }

    /**
     * 加到过滤器链上
     */
    @Bean
    public SpringSocialConfigurer securitySocialConfig() {
        return new SpringSocialConfigurer();
    }

}
