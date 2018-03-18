package net.zhuruyi.security.core.properties;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 22:47 2018/3/13
 * @Modified By:
 */

public class BrowserProperties {

    private String loginPage = "/login.html";

    private LoginType loginType = LoginType.JSON;

    /**
     * 记住我的时间长度 2
     */
    private int rememberMeSeconds = 3600;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
