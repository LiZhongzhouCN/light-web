package com.liz.light.repository;

import com.liz.light.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


/**
 * @author liz
 * @Description:
 * @date: 2020/7/8 15:50
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test(){
//        userRepository.deleteAll();
        long count = userRepository.count();
        //必须先判断是否存在
//        userRepository.deleteById(1L);
        List<User> list = userRepository.findAll();
        boolean exists = userRepository.existsById(1L);
        Optional<User> userOptional = userRepository.findById(4L);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            user.setUsername("haha");
            user.setAge(null);
            User user1 = userRepository.save(user);
        }
        List<User> usersByAge = userRepository.getUsersByAge(10);
        if (usersByAge.size() > 0)
        System.out.println(usersByAge.get(0).toString());
//        userRepository.saveAll();
    }
}