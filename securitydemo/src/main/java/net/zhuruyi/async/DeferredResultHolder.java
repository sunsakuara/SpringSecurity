package net.zhuruyi.async;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 21:36 2018/3/12
 * @Modified By:
 */
@Component
public class DeferredResultHolder {

    private Map<String, DeferredResult<String>> map = new HashMap<>();

    public Map<String, DeferredResult<String>> getMap() {
        return map;
    }

    public void setMap(
            Map<String, DeferredResult<String>> map) {
        this.map = map;
    }
}
