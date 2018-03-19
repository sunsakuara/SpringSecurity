package net.zhuruyi.security.core.Validata.code;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.zhuruyi.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 10:38 2018/3/17
 * @Modified By:
 */
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {


    private final SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private Set<String> urls = new HashSet<>();
    private SecurityProperties securityProperties;
    private AuthenticationFailureHandler authenticationFailHande;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(
                securityProperties.getCode().getSms().getUrl(), ",");
        if (configUrls != null && configUrls.length > 0) {
            for (String configUrl : configUrls) {
                urls.add(configUrl);
            }
        }

        urls.add("/authentication/mobile");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        boolean action = false;
        for (String url : urls) {
            if (pathMatcher.match(url, httpServletRequest.getRequestURI())) {
                action = true;
            }
        }

        if (action) {
            try {
                validate(new ServletWebRequest(httpServletRequest));
            } catch (ValidateCodeException e) {
                authenticationFailHande
                        .onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void validate(ServletWebRequest resuest) throws ServletRequestBindingException {
        ValidateCode codeInSession = (ValidateCode) sessionStrategy
                .getAttribute(resuest, ValidateCodeProcessor.SESSION_KEY_PREFIX + "SMS");

        String codeInRequest = ServletRequestUtils
                .getStringParameter(resuest.getRequest(), "smsCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeInSession.isExpried()) {
            sessionStrategy
                    .removeAttribute(resuest, ValidateCodeProcessor.SESSION_KEY_PREFIX + "SMS");
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(resuest, ValidateCodeProcessor.SESSION_KEY_PREFIX + "SMS");
    }


    public AuthenticationFailureHandler getAuthenticationFailHande() {
        return authenticationFailHande;
    }

    public void setAuthenticationFailHande(
            AuthenticationFailureHandler authenticationFailHande) {
        this.authenticationFailHande = authenticationFailHande;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(
            SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }
}





















