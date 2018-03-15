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
}
