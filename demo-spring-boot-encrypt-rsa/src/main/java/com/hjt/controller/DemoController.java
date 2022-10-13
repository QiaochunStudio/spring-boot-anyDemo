package com.hjt.controller;

import com.hjt.annotation.Decrypt;
import com.hjt.annotation.Encrypt;
import com.hjt.domain.TestBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Encrypt
    @GetMapping("/encryption")
    public TestBean encryption(@RequestParam("age")Integer age){
        TestBean testBean = new TestBean();
        testBean.setName("shuibo.cn");
//        testBean.setAge(18);
        testBean.setAge(age);
        long time = System.currentTimeMillis();
        testBean.setTimestamp(time);
        log.info("加密的内容："+testBean);
        return testBean;
    }

    @Decrypt
    @PostMapping("/decryption")
    public String Decryption(@RequestBody TestBean testBean){
        return testBean.toString();
    }
}
