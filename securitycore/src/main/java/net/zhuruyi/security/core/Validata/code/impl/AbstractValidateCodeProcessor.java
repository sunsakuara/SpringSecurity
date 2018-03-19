package net.zhuruyi.security.core.Validata.code.impl;

import java.util.Map;
import net.zhuruyi.security.core.Validata.code.ValidateCode;
import net.zhuruyi.security.core.Validata.code.ValidateCodeException;
import net.zhuruyi.security.core.Validata.code.ValidateCodeGenerator;
import net.zhuruyi.security.core.Validata.code.ValidateCodeProcessor;
import net.zhuruyi.security.core.Validata.code.ValidateCodeType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 21:57 2018/3/18
 * @Modified By:
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements
        ValidateCodeProcessor {

    /**
     * 操作session的工具类
     */
    private final SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGeneratos;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validataCode = generate(request);
        save(request, validataCode);
        send(request, validataCode);

    }

    /**
     * 生成校验码
     */
    private C generate(ServletWebRequest request) {
        String type = getValidateCodeType(request).toString().toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratos.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (C) validateCodeGenerator.generate(request);
    }

    /**
     * 保存验证码
     */
    private void save(ServletWebRequest request, C valicateCode) {
        sessionStrategy.setAttribute(request, getSessionKey(request), valicateCode);
    }

    /**
     * 根据请求的URL获取校验码的类型
     */
    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    /**
     * 构建验证码放入session时的key
     */
    private String getSessionKey(ServletWebRequest request) {
        return SESSION_KEY_PREFIX + getValidateCodeType(request).toString().toUpperCase();
    }

    protected abstract void send(ServletWebRequest request, C calidateCode) throws Exception;


}

















