package net.zhuruyi.security.core.social.qq.connect;

import net.zhuruyi.security.core.social.qq.api.QQ;
import net.zhuruyi.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 23:39 2018/3/22
 * @Modified By:
 */
public class QQAdapter implements ApiAdapter<QQ> {

    /**
     * 测试服务是否可用
     */
    @Override
    public boolean test(QQ qq) {
        return true;
    }

    /**
     * 做适配
     */
    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {
       
        QQUserInfo qqUserInfo = qq.getUserInfo();
        connectionValues.setDisplayName(qqUserInfo.getNickname());
        connectionValues.setImageUrl(qqUserInfo.getFigureurl_qq_1());
        /**个人主页的URL*/
        connectionValues.setProfileUrl(null);
        connectionValues.setProviderUserId(qqUserInfo.getOpenId());

    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    @Override
    public void updateStatus(QQ qq, String s) {

    }
}
