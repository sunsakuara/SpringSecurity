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

    private BrowserProperties browser = new BrowserProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}
