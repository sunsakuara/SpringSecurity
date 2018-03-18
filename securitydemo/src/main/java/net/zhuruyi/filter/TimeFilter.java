package net.zhuruyi.filter;

import java.io.IOException;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 16:34 2018/3/10
 * @Modified By:
 */
@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        //System.out.println("time filter start");
        Long start = new Date().getTime();
        filterChain.doFilter(servletRequest, servletResponse);
        // System.out.println("file fileter:耗時" + (new Date().getTime() - start));
        // System.out.println("time filter end");

    }

    @Override
    public void destroy() {
        System.out.println("time filter destory");
    }
}
