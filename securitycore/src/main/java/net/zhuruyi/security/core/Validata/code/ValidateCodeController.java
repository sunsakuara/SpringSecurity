package net.zhuruyi.security.core.Validata.code;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 9:41 2018/3/17
 * @Modified By:
 */
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

      /* private final SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;*/

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;


   /* @GetMapping("/code/image")
    public void createCode(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws IOException {
        //1。生成一個隨機數
        ImageCode imageCode = (ImageCode) imageCodeGenerator
                .generate(new ServletWebRequest(httpServletRequest));
        //2.将随机数传入session中
        sessionStrategy
                .setAttribute(new ServletWebRequest(httpServletRequest), SESSION_KEY, imageCode);
        //3.将图片写入到响应中
        ImageIO.write(imageCode.getImage(), "JPEG", httpServletResponse.getOutputStream());
    }

    @GetMapping("/code/sms")
    public void createSMS(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse)
            throws IOException, ServletRequestBindingException {
        //1。生成一個隨機數
        ValidateCode smsgeCode = smsCodeGenerator
                .generate(new ServletWebRequest(httpServletRequest));
        //2.将随机数传入session中
        sessionStrategy
                .setAttribute(new ServletWebRequest(httpServletRequest), SESSION_KEY, smsgeCode);
        //连接短信服务商  发送
        String mobile = ServletRequestUtils
                .getRequiredStringParameter(httpServletRequest, "mobile");
        smsCodeSender.send(mobile, smsgeCode.getCode());
    }*/

    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String type)
            throws Exception {
        validateCodeProcessors.get(type + "ValidateCodeProcessor")
                .create(new ServletWebRequest(request, response));
    }


}



















