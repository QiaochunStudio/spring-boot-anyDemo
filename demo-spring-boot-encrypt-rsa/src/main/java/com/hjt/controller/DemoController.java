package com.hjt.controller;

import com.hjt.annotation.Decrypt;
import com.hjt.annotation.Encrypt;
import com.hjt.domain.TestBean;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {
    @Encrypt
    @GetMapping("/encryption")
    public TestBean encryption(@RequestParam("age")Integer age){
        TestBean testBean = new TestBean();
        testBean.setName("shuibo.cn");
//        testBean.setAge(18);
        testBean.setAge(age);
        long time = System.currentTimeMillis();
        testBean.setTimestamp(time);
        return testBean;
    }

    @Decrypt
    @PostMapping("/decryption")
    public String Decryption(@RequestBody TestBean testBean){
        return testBean.toString();
    }
}
