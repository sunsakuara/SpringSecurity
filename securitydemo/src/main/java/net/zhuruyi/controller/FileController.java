package net.zhuruyi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.zhuruyi.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 22:17 2018/3/10
 * @Modified By:
 */

@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * @deprecated 文件上传
     */
    @PostMapping
    public net.zhuruyi.dto.FileInfo update(MultipartFile file) throws IOException {

        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        String folder = "D:\\IDEA Projects\\SecurityProject\\securitydemo\\src\\main\\java\\net\\zhuruyi\\controller";

        File localFile = new File(folder, new Date().getTime() + ".txt");
        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }


    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request,
            HttpServletResponse response) {
        String folder = "D:\\IDEA Projects\\SecurityProject\\securitydemo\\src\\main\\java\\net\\zhuruyi\\controller";
        try (
                InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
                OutputStream outputStream = response.getOutputStream()
        ) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Dispostion", "attachment;filename=text.txt");

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
















