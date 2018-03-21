package net.zhuruyi.security.core.Validata.code;

import net.zhuruyi.security.core.Validata.code.image.ImageCodeGenerator;
import net.zhuruyi.security.core.Validata.code.sms.DefaultSmsCodeSender;
import net.zhuruyi.security.core.Validata.code.sms.SmsCodeSender;
import net.zhuruyi.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 15:27 2018/3/17
 * @Modified By:
 */

@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;


    /**
     * @ConditionalOnMissingBean 仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender SmsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
