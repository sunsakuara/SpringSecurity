package net.zhuruyi.security.core.Validata.code.sms;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 18:42 2018/3/18
 * @Modified By:
 */
public interface SmsCodeSender {

    void send(String mobile, String code);
}
