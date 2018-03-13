package net.zhuruyi.security.core;

import net.zhuruyi.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 22:58 2018/3/13
 * @Modified By:
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
