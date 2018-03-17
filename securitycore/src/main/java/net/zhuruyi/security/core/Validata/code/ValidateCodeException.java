package net.zhuruyi.security.core.Validata.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 10:45 2018/3/17
 * @Modified By:
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
