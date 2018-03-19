package net.zhuruyi.security.core.Validata.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 15:21 2018/3/17
 * @Modified By:
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest httpServletRequest);

}
