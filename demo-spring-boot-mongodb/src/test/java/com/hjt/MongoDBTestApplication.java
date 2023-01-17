package com.hjt;

import com.hjt.entity.User;
import com.hjt.repository.impl.UserRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author :hjt
 * @date : 2023/1/17
 */

@SpringBootApplication
@SpringBootTest(classes = MongoDBTestApplication.class)
@RunWith(SpringRunner.class)
public class MongoDBTestApplication {
    public static final Logger log = LoggerFactory.getLogger(MongoDBTestApplication.class);

    @Resource
    private UserRepositoryImpl userRepository;

    @Test
    public   void test(){
        User user = new User();
        user.setId(1l);
        user.setUserName("hjt");
        user.setPassWord("1234");
        userRepository.saveUser(user);
    }

}
