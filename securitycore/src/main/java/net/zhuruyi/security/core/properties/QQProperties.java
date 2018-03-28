package net.zhuruyi.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 21:46 2018/3/27
 * @Modified By:
 */
public class QQProperties extends SocialProperties {

    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
