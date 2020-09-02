package com.liz.light.repository;

import com.liz.light.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author liz
 * @Description:
 * @date: 2020/7/9 17:54
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword("1fedc5a36d03c185065dd2b323886aa5");
        user.setAge(22);
        user.setStatus('1');
        userRepository.save(user);
    }
}