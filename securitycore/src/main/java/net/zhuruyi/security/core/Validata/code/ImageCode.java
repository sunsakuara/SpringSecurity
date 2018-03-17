package net.zhuruyi.security.core.Validata.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 9:35 2018/3/17
 * @Modified By:
 */
public class ImageCode {

    private BufferedImage image;
    private String code;
    private LocalDateTime expireTime;

    public ImageCode() {
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }


    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "ImageCode{" +
                "image=" + image +
                ", code='" + code + '\'' +
                ", expireTime=" + expireTime +
                '}';
    }

}
