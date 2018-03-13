package net.zhuruyi.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 22:47 2018/3/13
 * @Modified By:
 */
@ConfigurationProperties(prefix = "net.zhuruyi.security")
public class SecurityProperties {

    private final BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }
}
