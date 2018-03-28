package net.zhuruyi.security.core.social.qq.connect;

import net.zhuruyi.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 23:50 2018/3/22
 * @Modified By:
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
