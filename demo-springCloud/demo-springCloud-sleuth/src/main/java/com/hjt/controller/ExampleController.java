package com.hjt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class ExampleController {
    private static final Logger log = LoggerFactory.getLogger(ExampleController.class);

    @RequestMapping("/")
    public String home(@RequestHeader Map<String, String> headers) {
        log.info("headers:{}", headers);
//        int i = 1/0;
        log.info("Hello world!");
        return "Hello World!";
    }
}

