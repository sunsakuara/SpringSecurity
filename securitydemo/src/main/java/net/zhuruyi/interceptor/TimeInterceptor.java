package net.zhuruyi.interceptor;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 17:03 2018/3/10
 * @Modified By:
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Object o) throws Exception {
        /*System.out.println("preHandle");

        System.out.println(((HandlerMethod) o).getBean().getClass().getName());
        System.out.println(((HandlerMethod) o).getMethod().getName());*/

        httpServletRequest.setAttribute("startTime", new Date().getTime());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
            throws Exception {
        // System.out.println("postHandle");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        // System.out.println("time interrupter 耗时:" + (new Date().getTime() - start));

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // System.out.println("afterCompletion");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        //  System.out.println("time interrupter 耗时:" + (new Date().getTime() - start));

        // System.out.println("ex is" + e);

    }
}
