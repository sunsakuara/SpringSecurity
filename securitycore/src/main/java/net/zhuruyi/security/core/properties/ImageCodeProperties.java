package net.zhuruyi.security.core.properties;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 11:55 2018/3/17
 * @Modified By:
 */
public class ImageCodeProperties extends SmsCodeProperties {

    private int width = 67;
    private int height = 23;

    public ImageCodeProperties() {
        setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
