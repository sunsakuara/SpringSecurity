package net.zhuruyi.security.core.Validata.code.sms;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 18:44 2018/3/18
 * @Modified By:
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机" + mobile + "发送短信验证码:" + code);
    }
}
