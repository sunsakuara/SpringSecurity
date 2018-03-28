package net.zhuruyi.security.core.social.qq.config;

import net.zhuruyi.security.core.properties.QQProperties;
import net.zhuruyi.security.core.properties.SecurityProperties;
import net.zhuruyi.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 21:56 2018/3/27
 * @Modified By:
 */
@Configuration
@ConditionalOnProperty(prefix = "net.zhuruyi.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQQ();
        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(),
                qqConfig.getAppSecret());
    }
}
