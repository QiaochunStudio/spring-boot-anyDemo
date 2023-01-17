package com.hjt.controller;

import com.hjt.repository.impl.UserRepositoryImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author :hjt
 * @date : 2023/1/17
 */
@RestController
@RequestMapping("/testUserController")
public class TestUserController {
    @Resource
    private UserRepositoryImpl userRepository;




}
