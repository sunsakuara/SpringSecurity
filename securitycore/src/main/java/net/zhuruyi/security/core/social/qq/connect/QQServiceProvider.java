package net.zhuruyi.security.core.social.qq.connect;

import net.zhuruyi.security.core.social.qq.api.QQ;
import net.zhuruyi.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 23:23 2018/3/22
 * @Modified By:
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private static final String URL_AUTHORRIZE = "https://graph.qq.com/oauth2.0/authorize";
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
    private String appId;

    public QQServiceProvider(
            String appId, String appSecret) {
        /**TODO QQ互联 appId：用户名账户 appSercrect 用户名密码*/
        /**
         * URL_AUTHORRIZE 第一步
         * URL_ACCESS_TOKEN 第4步*/
        super(new OAuth2Template(appId, appSecret, URL_AUTHORRIZE, URL_ACCESS_TOKEN));

    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
