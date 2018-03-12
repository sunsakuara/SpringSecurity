package net.zhuruyi.config;

import java.util.ArrayList;
import java.util.List;
import net.zhuruyi.filter.TimeFilter;
import net.zhuruyi.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 16:57 2018/3/10
 * @Modified By:
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    public TimeInterceptor timeInterceptor;

    /**
     * 处理异步请求
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        super.configureAsyncSupport(configurer);
        /*configurer.registerCallableInterceptors();
        configurer.registerDeferredResultInterceptors();*/
    }


    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);

        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }
}
