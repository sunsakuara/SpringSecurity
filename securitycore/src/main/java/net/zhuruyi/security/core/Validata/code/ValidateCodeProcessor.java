package net.zhuruyi.security.core.Validata.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 21:52 2018/3/18
 * @Modified By:
 */
public interface ValidateCodeProcessor {

    /**
     * 验证吗放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建校验码
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     */
    void validate(ServletWebRequest servletWebRequest);

}
