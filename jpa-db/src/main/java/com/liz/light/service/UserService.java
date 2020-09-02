package com.liz.light.service;

import com.liz.light.entity.User;

import java.util.List;

/**
 * @author liz
 * @Description:
 * @date: 2020/7/9 11:08
 */
public interface UserService {

    Long count();

    List<User> findAll();

    Boolean exists();





}
