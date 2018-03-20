package net.zhuruyi.security.core.Validata.code;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.zhuruyi.security.core.properties.SecurityConstants;
import net.zhuruyi.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 10:38 2018/3/17
 * @Modified By:
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /*private final SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();*/

    /**
     * 存放所有需要检验验证码的URL
     */
    private final Map<String, ValidateCodeType> urlMap = new HashMap<>();
    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    /**
     * 验证码校验失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    /**
     * 系统配置信息
     */
    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 系统中的校验码处理器
     */
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;


    /**
     * 初始化要拦截的url配置信息
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);

    }

    /**
     * 将系统中配置的需要校验的验证码的URL根据校验的类型放入map
     */
    protected void addUrlToMap(String urlString, ValidateCodeType type) {
        if (StringUtils.isNotBlank(urlString)) {
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
            for (String url : urls) {
                urlMap.put(url, type);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        ValidateCodeType type = getValidateCodeType(httpServletRequest);
        if (type != null) {
            logger.info("校验请求（" + httpServletRequest.getRequestURI() + ")中的验证码，验证码类型" + type);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type)
                        .validate(new ServletWebRequest(httpServletRequest, httpServletResponse));
                logger.info("验证码校验通过");
            } catch (ValidateCodeException exception) {
                authenticationFailureHandler
                        .onAuthenticationFailure(httpServletRequest, httpServletResponse,
                                exception);
                return;
            }
        }
    }

    /**
     * 获取检验码的类型，如果当前请求不需要校验，则返回null
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest httpServletRequest) {
        ValidateCodeType result = null;
        if (!StringUtils.endsWithIgnoreCase(httpServletRequest.getMethod(), "get")) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls) {
                if (pathMatcher.match(url, httpServletRequest.getRequestURI())) {
                    result = urlMap.get(url);
                }
            }
        }
        return result;
    }

    /*private void validate(ServletWebRequest resuest) throws ServletRequestBindingException {
        ImageCode codeInSession = (ImageCode) sessionStrategy
                .getAttribute(resuest, ValidateCodeController.SESSION_KEY);
        String codeInRequest = ServletRequestUtils
                .getStringParameter(resuest.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeInSession.isExpried()) {
            sessionStrategy.removeAttribute(resuest, ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(resuest, ValidateCodeController.SESSION_KEY);
    }*/

   /* public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(
            SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }*/
}





















