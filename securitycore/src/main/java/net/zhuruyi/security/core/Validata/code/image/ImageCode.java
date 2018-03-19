package net.zhuruyi.security.core.Validata.code.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import net.zhuruyi.security.core.Validata.code.ValidateCode;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 9:35 2018/3/17
 * @Modified By:
 */
public class ImageCode extends ValidateCode {

    private BufferedImage image;

    public ImageCode() {
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }


    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;

    }


    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }


}
