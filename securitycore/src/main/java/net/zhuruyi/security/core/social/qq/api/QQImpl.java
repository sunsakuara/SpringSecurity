package net.zhuruyi.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 22:07 2018/3/22
 * @Modified By:
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    /**
     * 需要的三个参数 accessToken appid openID 兩個路徑
     */
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    /**
     * accessToken由父类来处理 获取用户信息的地址
     */
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?auth_consumer_key=%s&openid=%s";

    private final String appId;
    private final String openId;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * accessToken 走完Oauth流程，拿到令牌 appId 系统的配置信息
     */
    public QQImpl(String accessTocken, String appId) {
        /*父类会自动处理accessToken*/
        super(accessTocken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPENID, accessTocken);
        String result = getRestTemplate().getForObject(url, String.class);
        System.out.println(result);
        openId = StringUtils.substringBetween(result, "\"openid\":", "}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        System.out.println(result);

        try {
            /**字符串读成一个UserInfo对象*/
            return objectMapper.readValue(result, QQUserInfo.class);
        } catch (IOException e) {
            throw new RuntimeException("获取信息失败");
        }
    }
}
