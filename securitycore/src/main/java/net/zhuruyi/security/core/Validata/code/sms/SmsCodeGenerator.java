package net.zhuruyi.security.core.Validata.code.sms;

import net.zhuruyi.security.core.Validata.code.ValidateCode;
import net.zhuruyi.security.core.Validata.code.ValidateCodeGenerator;
import net.zhuruyi.security.core.properties.SecurityProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 15:23 2018/3/17
 * @Modified By:
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 生成图形验证码
     */
    @Override
    public ValidateCode generate(ServletWebRequest httpServletRequest) {
        String code = RandomStringUtils
                .randomNumeric(securityProperties.getCode().getSms().getLength());

        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

    /**
     * 生成随机背景条纹
     */
    /*private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }*/
    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(
            SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
