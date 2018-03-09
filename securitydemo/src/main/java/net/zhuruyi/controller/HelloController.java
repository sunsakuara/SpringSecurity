package net.zhuruyi.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class HelloController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(HelloController.class);
    

    @GetMapping("/hello")
    public String hello() {
        return "hello spring security";
    }

}
