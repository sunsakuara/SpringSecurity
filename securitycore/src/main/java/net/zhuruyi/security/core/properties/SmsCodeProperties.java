package net.zhuruyi.security.core.properties;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 21:24 2018/3/18
 * @Modified By:
 */
public class SmsCodeProperties {

    private int length = 4;
    private int expireIn = 60;
    private String url;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
