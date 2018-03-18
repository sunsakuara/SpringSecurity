package net.zhuruyi.aspect;

import java.util.Date;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 20:19 2018/3/10
 * @Modified By:
 */
@Aspect
@Component
public class TimeAspect {

    @Around("execution(* net.zhuruyi.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        // System.out.println("time aspect start");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is" + arg);
        }

        Long start = new Date().getTime();
        Object o = pjp.proceed();
       /* System.out.println();
        System.out.println("file fileter:耗時" + (new Date().getTime() - start));
        System.out.println("time aspect end");*/

        return o;
    }

}
