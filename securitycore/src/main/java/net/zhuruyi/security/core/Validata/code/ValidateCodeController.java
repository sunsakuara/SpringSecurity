package net.zhuruyi.security.core.Validata.code;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator validateCodeGenerator;


    @GetMapping("/code/image")
    public void createCode(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws IOException {
        //1。生成一個隨機數
        ImageCode imageCode = validateCodeGenerator
                .generate(new ServletWebRequest(httpServletRequest));
        //2.将随机数传入session中
        sessionStrategy
                .setAttribute(new ServletWebRequest(httpServletRequest), SESSION_KEY, imageCode);
        //3.将图片写入到响应中
        ImageIO.write(imageCode.getImage(), "JPEG", httpServletResponse.getOutputStream());
    }


}



















