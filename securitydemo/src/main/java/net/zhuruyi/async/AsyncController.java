package net.zhuruyi.async;


import java.util.concurrent.Callable;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 21:01 2018/3/12
 * @Modified By:
 */

@RestController
public class AsyncController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder resultHolder;


    /**
     * 异步处理： 1.Callable
     */

    @GetMapping("/order")
    public Callable<String> order() throws Exception {
        logger.info("主线程开始");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);
                logger.info("副线程結束");
                return null;
            }
        };
        logger.info("主线程结束");

        return result;
    }

    @GetMapping("/order2")
    public DeferredResult<String> order2() throws Exception {
        logger.info("主线程开始");
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);
        DeferredResult<String> result = new DeferredResult<>();
        resultHolder.getMap().put(orderNumber, result);

        logger.info("主线程结束");

        return result;

    }


}






















