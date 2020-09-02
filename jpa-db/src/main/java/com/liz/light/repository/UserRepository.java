package com.liz.light.repository;

import com.liz.light.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liz
 * @Description:
 * @date: 2020/7/8 15:40
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "select * from t_user where age = :age",nativeQuery = true)
    List<User> getUsersByAge(@Param("age") Integer age);
}
